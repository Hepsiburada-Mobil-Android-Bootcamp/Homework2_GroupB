package com.noor.homework2_groupb.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.service.Firestore
import com.noor.homework2_groupb.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate,false) {
    val firebase by lazy { Firestore() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            firebase.signUpUser(binding.eTextEmail.text.toString(),binding.eTextPassword.text.toString())
        }

    }
}