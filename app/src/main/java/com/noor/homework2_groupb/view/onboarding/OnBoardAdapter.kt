package com.noor.homework2_groupb.view.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.noor.homework2_groupb.view.onboarding.onboardscreen.ErrorOnBoardFragment
import com.noor.homework2_groupb.view.onboarding.onboardscreen.FirstOnBoardFragment
import com.noor.homework2_groupb.view.onboarding.onboardscreen.SecondOnBoardFragment

class OnBoardAdapter(fragment:FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FirstOnBoardFragment()
            1 -> SecondOnBoardFragment()
            else -> ErrorOnBoardFragment()
        }
    }
}