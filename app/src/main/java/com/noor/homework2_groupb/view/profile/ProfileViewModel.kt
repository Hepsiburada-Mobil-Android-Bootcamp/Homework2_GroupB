package com.noor.homework2_groupb.view.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.User

const val COLLECTION_PATH_USER = "users"

class ProfileViewModel : ViewModel() {


    private val db = FirebaseFirestore.getInstance()
    private val currentUser=FirebaseAuth.getInstance().currentUser!!.uid

    val user by lazy { MutableLiveData<User>() }

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


}