package com.noor.homework2_groupb.view.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.User
import com.noor.homework2_groupb.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate, false) {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            signUpUser(binding.eTextEmail.text.toString(), binding.eTextPassword.text.toString())
        }
    }

    private fun signUpUser(email: String, password: String) {

        val user by lazy { User(email, password) }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(R.id.signUpFragment_to_loginFragment)
                db.collection("users").add(user).addOnCompleteListener {
                    when (it.isSuccessful) {
                        true -> {
                            Log.d("firebaseData", "success")
                            Toast.makeText(context, "Sign Up Success", Toast.LENGTH_SHORT).show()
                        }
                        false -> {
                            Log.d("firebaseData", "fail")
                            Toast.makeText(context, "Sign Up Fail", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Log.d("firebaseAuth", "fail")
            }
        }
    }
}