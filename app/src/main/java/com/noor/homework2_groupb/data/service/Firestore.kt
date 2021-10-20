package com.noor.homework2_groupb.data.service

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.User

class Firestore {

    private val ref by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val currentFirebaseUser by lazy { FirebaseAuth.getInstance().currentUser }



    fun signUpUser(email:String,password:String){

        val user by lazy { User(email,password) }
        ref.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
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
}