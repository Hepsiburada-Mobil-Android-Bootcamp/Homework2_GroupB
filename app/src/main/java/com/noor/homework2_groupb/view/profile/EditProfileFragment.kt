package com.noor.homework2_groupb.view.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.User
import com.noor.homework2_groupb.databinding.FragmentEditProfileBinding


class EditProfileFragment :  BaseFragment<FragmentEditProfileBinding>(FragmentEditProfileBinding::inflate, true) {

    private val viewModel by viewModels<ProfileViewModel>()
    private val viewModel2 by viewModels<EditProfileViewModel>()
    private val db = FirebaseFirestore.getInstance()
    private val currentUser= FirebaseAuth.getInstance().currentUser!!.uid

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfileFromFirebase()
        viewModel.user.observe(viewLifecycleOwner){
            binding.user = it
        }

        binding.buttonSave.setOnClickListener {
            val name = binding.eTextName.text.toString()
            val surname = binding.eTextSurname.text.toString()
            val address = binding.eTextAddress.text.toString()
            val phoneNumber =  binding.eTextPhoneNumber.text.toString()
            val username = binding.eTextUsername.text.toString()
            //val image = binding.imgUser.value.toString()
            viewModel2.updateUserFromFirebase(user = User(username,name,surname,address,phoneNumber) )
        }
    }
}