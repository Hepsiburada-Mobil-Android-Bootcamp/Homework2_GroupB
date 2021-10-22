package com.noor.homework2_groupb.view.addproduct

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.noor.homework2_groupb.data.model.Product
import java.text.SimpleDateFormat
import java.util.*

class AddProductViewModel : ViewModel() {

    val isShowProgressBar: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val isShowSuccessfulAnimation: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val productImageUrl: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val productType: MutableLiveData<String> by lazy {
        MutableLiveData<String>("wearable")
    }

    private val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
    private val fileName: String = formatter.format(Date())

    private val ref = FirebaseStorage.getInstance().getReference("product/images/$fileName")
    private val db = FirebaseFirestore.getInstance()

    fun uploadImageToFirebase(imageUri: Uri) {
        isShowProgressBar.value = true
        ref.putFile(imageUri).addOnSuccessListener { _ ->
            ref.downloadUrl.addOnSuccessListener { uri ->
                Log.d("DOWNLOADED_URL", uri.toString())
                productImageUrl.value = uri.toString()
                isShowProgressBar.value = false
            }
        }.addOnCanceledListener {
            isShowProgressBar.value = false
        }
    }

    fun uploadProductToFirebase(product: Product) {
        isShowProgressBar.value = true
        db.collection("product").add(product).addOnSuccessListener {
            Log.d("SUCCESSFUL_LOADED", product.toString())
            isShowProgressBar.value = false
            isShowSuccessfulAnimation.value = true
        }
    }
}