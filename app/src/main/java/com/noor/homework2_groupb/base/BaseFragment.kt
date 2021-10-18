package com.noor.homework2_groupb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.noor.homework2_groupb.MainActivity

abstract class BaseFragment<DB : ViewDataBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> DB,
    private val isNeedBottomNavBar: Boolean = false
) : Fragment()  {

    private var _binding: DB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        showBottomNavBar()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showBottomNavBar() {
        if (isNeedBottomNavBar)
            (activity as MainActivity).showNavigationBar()
        else
            (activity as MainActivity).hideNavigationBar()
    }
}