package com.noor.homework2_groupb.view.profile


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.User


class EditProfileViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser!!.uid

    val user by lazy { MutableLiveData<User>() }

    fun updateUserFromFirebase(user: User){
        val user = hashMapOf(
            "name" to user.name,
            "surname" to user.surname,
            "address" to user.address,
            "phoneNumber" to user.phoneNumber,
            "username" to user.username,
            "img" to user.img
        )

        db.collection("users").document(currentUser).set(user).addOnSuccessListener {
            Log.d("Update", "DocumentSnapshot successfully updated!")
        }

    }



}