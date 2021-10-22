package com.noor.homework2_groupb.view.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.databinding.FragmentProfileBinding

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate, true) {

//    private val viewModel by viewModels<ProfileViewModel>()
//    private val user = FirebaseAuth.getInstance().currentUser?.uid
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        Log.d("HomeFragment-", user.toString())
//        viewModel.currentUser = user.toString()
//        viewModel.getProfileFromFirebase()
//
//        viewModel.user.observe(viewLifecycleOwner) {
//            binding.user = it
//        }
//
//        binding.btnSignOut.setOnClickListener {
//            Firebase.auth.signOut()
//            findNavController().navigate(R.id.profileFragment_to_loginFragment)
//        }
//    }
}