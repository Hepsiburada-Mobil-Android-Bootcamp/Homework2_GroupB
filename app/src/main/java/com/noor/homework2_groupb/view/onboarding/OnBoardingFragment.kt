package com.noor.homework2_groupb.view.onboarding

import android.os.Bundle
import android.view.View
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.databinding.FragmentOnBoardingBinding
import com.noor.homework2_groupb.view.onboarding.animation.CubeOutDepthTransformation

class OnBoardingFragment :
    BaseFragment<FragmentOnBoardingBinding>(FragmentOnBoardingBinding::inflate) {

    private val adapter by lazy { OnBoardAdapter(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onboardingViewPager.setPageTransformer(CubeOutDepthTransformation())
        binding.onboardingViewPager.adapter = adapter
    }
}