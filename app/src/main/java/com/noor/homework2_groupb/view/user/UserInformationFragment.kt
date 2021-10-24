package com.noor.homework2_groupb.view.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.User
import com.noor.homework2_groupb.databinding.FragmentUserInformationBinding


class UserInformationFragment:  BaseFragment<FragmentUserInformationBinding>
    (FragmentUserInformationBinding::inflate, false){

    val viewModel by viewModels<UserInformationViewModel>()
    val user by lazy { User() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            editProfile()
        }
    }
    private fun editProfile(){
        if(isValid()) {
            viewModel.editProfile(
                binding.eTextUsername.text.toString(),
                binding.eTextName.text.toString(),
                binding.eTextSurname.text.toString(),
                binding.eTextAddress.text.toString(),
                binding.eTextPhoneNumber.text.toString().toInt(),
                user.isUserInformationSeen
            )
            redirect()
        }
    }

    private fun redirect(){
        findNavController().navigate(R.id.userInformationFragment_to_homeFragment)
    }

    private fun isValid():Boolean{
        binding.apply {
            return when {
                binding.eTextUsername.text.isNullOrEmpty() -> {
                    binding.eTextUsername.error = "Please fill in the blanks"
                    false
                }
                binding.eTextName.text.isNullOrEmpty() -> {
                    binding.eTextName.error = "Please fill in the blanks"
                    false
                }
                binding.eTextSurname.text.isNullOrEmpty() -> {
                    binding.eTextSurname.error = "Please fill in the blanks"
                    false
                }
                binding.eTextAddress.text.isNullOrEmpty() -> {
                    binding.eTextAddress.error = "Please fill in the blanks"
                    false
                }
                binding.eTextPhoneNumber.text.isNullOrEmpty() -> {
                    binding.eTextPhoneNumber.error = "Please fill in the blanks"
                    false
                }
                else -> true
            }
        }

    }
}