package com.noor.homework2_groupb.view.login

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.local.SharedPrefManager
import com.noor.homework2_groupb.data.model.User
import com.noor.homework2_groupb.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate,false) {

    private val auth by lazy { FirebaseAuth.getInstance() }
    val viewmodel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_toSignUpFragment)
        }
        binding.btnLogin.setOnClickListener() {
            signInUser(binding.eTextEmail.text.toString(),binding.eTextPassword.text.toString())

        }
    }

    private fun signInUser(email:String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                    updateUI(auth.currentUser)
                }else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            checkOldUser()
        }else {
            Toast.makeText(context,"Sign in failed",Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkOldUser(){
        viewmodel.isSeen.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }else
                findNavController().navigate(R.id.action_loginFragment_to_userInformationFragment)
        }
        viewmodel.isOldUser()
    }

}






