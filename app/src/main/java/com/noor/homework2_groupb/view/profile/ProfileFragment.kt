package com.noor.homework2_groupb.view.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.databinding.FragmentProfileBinding
import com.noor.homework2_groupb.view.addproduct.REQUEST_CODE_FOR_TAKE_PICTURE

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate, true) {

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getProfileFromFirebase()
        viewModel.user.observe(viewLifecycleOwner) {
            binding.user = it
        }
        binding.btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.profileFragment_to_loginFragment)
        }
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_fragment_edit_profile)
        }
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }
        binding.imageUser.setOnClickListener {
            goGalleryForTakePicture()
        }
    }

    private fun goGalleryForTakePicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_FOR_TAKE_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_FOR_TAKE_PICTURE) {
            if (data != null) {
                binding.imageUser.setImageURI(data.data) // handle chosen image
                viewModel.uploadUserImage(data.data!!) // upload chosen image
            }
        }
    }

}
