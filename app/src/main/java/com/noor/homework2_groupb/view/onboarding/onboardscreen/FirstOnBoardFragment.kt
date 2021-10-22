package com.noor.homework2_groupb.view.onboarding.onboardscreen

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.databinding.FragmentFirstOnBoardBinding

class FirstOnBoardFragment :
    BaseFragment<FragmentFirstOnBoardBinding>(FragmentFirstOnBoardBinding::inflate) {

    private val onboardViewPager by lazy { activity?.findViewById<ViewPager2>(R.id.onboardingViewPager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGotoSecond.setOnClickListener {
            onboardViewPager?.currentItem = 1
        }
    }
}