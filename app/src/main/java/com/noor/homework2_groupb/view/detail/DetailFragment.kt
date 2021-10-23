package com.noor.homework2_groupb.view.detail

import android.os.Bundle
import android.view.View
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.product = arguments?.getSerializable("product") as Product
    }

}