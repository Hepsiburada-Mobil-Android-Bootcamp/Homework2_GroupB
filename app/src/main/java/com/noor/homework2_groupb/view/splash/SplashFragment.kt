package com.noor.homework2_groupb.view.splash

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.noor.homework2_groupb.R

import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.local.SharedPrefManager
import com.noor.homework2_groupb.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate, false) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottieAnimationListener()
    }

    private fun lottieAnimationListener(){
        binding.lottieAnimation.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
                Log.v("Animation","Animation started")
            }

            override fun onAnimationEnd(p0: Animator?) {
                when{
                    isOnboardSeen()-> findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    else -> findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
                }
            }

            override fun onAnimationCancel(p0: Animator?) {
                Log.v("Animation","Animation cancelled")
            }

            override fun onAnimationRepeat(p0: Animator?) {
                Log.v("Animation","Animation repeated")
            }

        })
    }
    private fun isOnboardSeen():Boolean=SharedPrefManager(requireContext()).isOnboardSeen()
}