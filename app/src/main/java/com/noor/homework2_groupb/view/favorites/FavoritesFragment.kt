package com.noor.homework2_groupb.view.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.FragmentFavoritesBinding

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate, true) {

    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavorites.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel._productList.observe(viewLifecycleOwner) {
            binding.rvFavorites.adapter = FavoriteAdapter(it) { product ->
                navigateToDetail(product)
            }
        }

        viewModel.getFavoritesFromFirebase()

    }

    private fun navigateToDetail(product: Product) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(product)
        findNavController().navigate(action)
    }

}