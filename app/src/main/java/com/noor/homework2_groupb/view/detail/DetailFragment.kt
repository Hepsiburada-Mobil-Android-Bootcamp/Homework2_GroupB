package com.noor.homework2_groupb.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val product by lazy { arguments?.getSerializable("product") as Product }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.product = product

        viewModel.product = product
        viewModel.getProductId(product.description)
        viewModel.isFavorite()

        initLikeListener()
        initProgressBarListener()
    }

    private fun initLikeListener() {
        viewModel.isLiked.observe(viewLifecycleOwner) {
            binding.isLiked = it
        }
        binding.ibtnLikeProduct.setOnClickListener {
            when(viewModel.isLiked.value) {
                false -> {
                    viewModel.liked()
                    viewModel.isLiked.value = true
                }
                true -> {
                    viewModel.unliked()
                    viewModel.isLiked.value = false
                }
            }
        }
    }

    private fun initProgressBarListener() {
        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            when(it) {
                true -> binding.pbDetail.visibility = View.VISIBLE
                false -> binding.pbDetail.visibility = View.GONE
            }
        }
    }

}