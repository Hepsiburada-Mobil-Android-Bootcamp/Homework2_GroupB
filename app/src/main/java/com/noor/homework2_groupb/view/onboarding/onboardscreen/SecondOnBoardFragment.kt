package com.noor.homework2_groupb.view.onboarding.onboardscreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.local.SharedPrefManager
import com.noor.homework2_groupb.databinding.FragmentSecondOnBoardBinding

class SecondOnBoardFragment :
    BaseFragment<FragmentSecondOnBoardBinding>(FragmentSecondOnBoardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            SharedPrefManager(requireContext()).setOnboardSeen()
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

}