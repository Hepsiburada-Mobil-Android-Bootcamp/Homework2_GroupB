package com.noor.homework2_groupb.view.profile

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.noor.homework2_groupb.data.model.User
import java.io.File

const val COLLECTION_PATH_USER = "users"

class ProfileViewModel : ViewModel() {


    private val db = FirebaseFirestore.getInstance()
    private val currentUser=FirebaseAuth.getInstance().currentUser!!.uid
    private val ref = FirebaseStorage.getInstance().getReference("user/").child("profileImages").child(currentUser +".jpeg")

    val user by lazy { MutableLiveData<User>() }
    val userImageUrl: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getProfileFromFirebase() {
        db.collection(COLLECTION_PATH_USER).document(currentUser).get()
            .addOnSuccessListener {
                if(it!=null) {
                    user.value = it.toObject(User::class.java) as User
                    Log.d("it","Document ${user.value}")
                }else{
                    Log.d("it","No document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("it", "get failed with ", exception)
            }

    }
    fun uploadImageToFirebase(imageUri: Uri) {
        val profileUpdates = userProfileChangeRequest {
            photoUri = imageUri
        }
        currentUser.let{
            ref.putFile(imageUri).addOnSuccessListener { _ ->
                getDownloadedUrl()
            }.addOnCanceledListener {
                Log.d("seyma","fail + ${currentUser}")
            }
        }

    }
    private fun getDownloadedUrl() {
        ref.downloadUrl.addOnSuccessListener { uri ->
            Log.d("DOWNLOADED_URL", uri.toString())
            userImageUrl.value = uri.toString()
        }
    }
    fun uplo(imageUri: Uri){
        FirebaseAuth.getInstance().currentUser?.apply {
            val profileUpdates : UserProfileChangeRequest = UserProfileChangeRequest.Builder().
            setPhotoUri(imageUri).
            build()
            updateProfile(profileUpdates).addOnCompleteListener(OnCompleteListener {
                when(it.isSuccessful) {
                    true -> apply {
                        ref.putFile(imageUri).addOnSuccessListener { _ ->
                            getDownloadedUrl()
                        }
                        Log.d("seyma","success")
                    }
                    false -> Log.d("seyma","fail")
                }

            })
        }
}
}
