package com.noor.homework2_groupb.view.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.data.model.User
import com.noor.homework2_groupb.view.home.FIELD_NAME

const val COLLECTION_FAVORITES = "favorites"
const val COLLECTION_PRODUCT = "product"

class DetailViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser!!.uid


    val showProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val isLiked: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    private val likedList: ArrayList<String> = arrayListOf()
    var product: Product? = null
    private var productID: String = ""

    fun getProductId(productDesc: String) {
        showProgressBar.value = true
        db.collection(COLLECTION_PRODUCT).whereEqualTo("description", productDesc).addSnapshotListener { value, error ->
            productID = value?.documents!![0].id
            showProgressBar.value = false
        }
    }

    fun isFavorite() {
        db.collection(COLLECTION_FAVORITES).document(currentUser).get().addOnSuccessListener {
            likedList.addAll(it.get("name") as ArrayList<String>)
            isLiked.value = likedList.contains(productID)
            Log.d("ASDASDASD", likedList.contains(productID).toString())
        }
    }

    fun liked() {
        showProgressBar.value = true
        likedList.add(productID)
        val mappedLikeList = HashMap<String, ArrayList<String>>()
        mappedLikeList["name"] = likedList
        db.collection(COLLECTION_FAVORITES).document(currentUser).set(mappedLikeList)
            .addOnSuccessListener {
//                increaseLikeCount(productName)
            }.addOnCompleteListener {
                showProgressBar.value = false
            }
    }

    fun unliked() {
        showProgressBar.value = true
        likedList.remove(productID)
        val mappedLikeList = HashMap<String, ArrayList<String>>()
        mappedLikeList["name"] = likedList
        db.collection(COLLECTION_FAVORITES).document(currentUser).set(mappedLikeList)
            .addOnSuccessListener {
//                decreaseLikeCount(productName)
            }.addOnCompleteListener {
                showProgressBar.value = false
            }
    }

    fun delete(){
        db.collection("product").document(productID)
            .delete()
            .addOnSuccessListener { Log.d("silme", "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w("silme", "Error deleting document", e) }
    }

//    private fun increaseLikeCount(productName: String) {
//        getProductIdFromFirebase(productName)
//        db.collection(COLLECTION_PRODUCT).document(productId)
//            .update("likeCount", product!!.likeCount++).addOnSuccessListener {
//            showProgressBar.value = false
//        }
//    }
//
//    private fun decreaseLikeCount(productName: String) {
//        getProductIdFromFirebase(productName)
//        db.collection(COLLECTION_PRODUCT).document(productId)
//            .update("likeCount", product!!.likeCount--).addOnSuccessListener {
//                showProgressBar.value = false
//            }
//    }
//
//    private fun getProductIdFromFirebase(productName: String) {
//        db.collection(COLLECTION_PRODUCT).whereEqualTo(FIELD_NAME, productName).get()
//            .addOnSuccessListener {
//                Log.d("COLLECTION_PRODUCT", it.documents[0].id)
//                productId = it.documents[0].id
//            }
//    }

}