package com.noor.homework2_groupb.view.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.data.model.User
import com.noor.homework2_groupb.view.home.COLLECTION_PATH_PRODUCT
import com.noor.homework2_groupb.view.home.FIELD_NAME

const val COLLECTION_PATH_USER = "users"

class ProfileViewModel : ViewModel() {
//
//    val db = FirebaseFirestore.getInstance()
//    var currentUser: String = ""
//
//    val user: MutableLiveData<User> by lazy {
//        MutableLiveData<User>()
//    }
//
//    fun getProfileFromFirebase() {
//        db.collection(COLLECTION_PATH_USER).document(currentUser).get()
//            .addOnSuccessListener {
//                user.value = it.toObject(User::class.java) as User
//            }
//    }

}