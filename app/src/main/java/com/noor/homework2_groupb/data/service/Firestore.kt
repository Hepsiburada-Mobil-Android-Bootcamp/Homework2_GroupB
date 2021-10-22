package com.noor.homework2_groupb.data.service

import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.User

class Firestore {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }



    fun signUpUser(email:String,password:String){

        val user by lazy { User(email,password) }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                db.collection("users").add(user).addOnCompleteListener {
                    when(it.isSuccessful){
                        true -> Log.d("firebaseData", "success")
                        false-> Log.d("firebaseData","fail")
                    }
                }

            } else {
                Log.d("firebaseAuth", "fail")
            }
        }
    }
    private fun updateUI(user: FirebaseUser?) {}

}