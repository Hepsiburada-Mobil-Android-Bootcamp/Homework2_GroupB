package com.noor.homework2_groupb.view.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate, true){

    var firestore: FirebaseFirestore? = null
    val currentFirebaseUser by lazy { FirebaseAuth.getInstance().currentUser}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = FirebaseFirestore.getInstance()

    }



    fun getUser(){
        currentFirebaseUser?.let {
            firestore?.collection("users")?.document(it.uid)?.get()
                ?.addOnSuccessListener { documentSnapshot ->
                    if(documentSnapshot != null){
                        Log.d("firebase","DocumentSnapshot data : ${documentSnapshot.data}")
                        println("${documentSnapshot.data}")
                        binding.textNameAndSurname.text = documentSnapshot.getString("email").toString()
                        //binding.textNameAndSurname.visibility = View.VISIBLE
                    }
                    else{
                        Log.d("firebase","no such document")
                    }
                }?.addOnFailureListener { exception ->
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                    Log.d("firebase", "get failed with", exception)
                }
        }

    }

/*fun getUsername(context: Context, text_name_and_surname: TextView) {
        //val db = getDatabase()
        var firestore: FirebaseFirestore? = null
        val currentFirebaseUser = getCurrentFirebaseUser()

        firestore.collection("users").document(currentFirebaseUser.uid).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    Log.d("ffirebase", "DocumentSnapshot data : ${documentSnapshot.data}")
                    text_name_and_surname.text = documentSnapshot.getString("username").toString()
                    text_name_and_surname.visibility = View.VISIBLE

                } else {
                    Log.d("ffirebase", "No such document")
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                Log.d("ffirebase", "get failed with", exception)
            }
    }*/



}