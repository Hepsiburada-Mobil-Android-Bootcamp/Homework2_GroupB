package com.noor.homework2_groupb.view.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.FragmentHomeBinding
import com.noor.homework2_groupb.view.home.adapter.CategoryAdapter
import com.noor.homework2_groupb.view.home.adapter.MostLikedAdapter
import com.noor.homework2_groupb.view.home.adapter.SearchResultAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate, true) {

    private val viewModel by viewModels<HomeViewModel>()
    val productList: ArrayList<Product> by lazy { arrayListOf() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListenerForNavigateToAddProduct()
        initRecyclerViews()
        initListenerProductList()
        initSearchLogic()
        initChipListeners()
    }

    private fun initChipListeners() {
        viewModel.getProductsByCategoryFromFirebase(getString(R.string.wearabler))
        binding.chipWearable.setOnClickListener {
            viewModel.getProductsByCategoryFromFirebase(getString(R.string.wearabler))
        }
        binding.chipTablets.setOnClickListener {
            viewModel.getProductsByCategoryFromFirebase(getString(R.string.tablet))
        }
        binding.chipPhones.setOnClickListener {
            viewModel.getProductsByCategoryFromFirebase(getString(R.string.phone))
        }
        binding.chipLaptops.setOnClickListener {
            viewModel.getProductsByCategoryFromFirebase(getString(R.string.laptop))
        }
    }

    private fun initListenerForNavigateToAddProduct() {
        binding.ivAddProduct.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddProductFragment())
        }
    }

    private fun initRecyclerViews() {

        initRvCategory()
        initRvMostLiked()
        initRvSearch()

    }

    private fun initRvMostLiked() {
        binding.rvMostLikedProducts.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.mostLikedProducts.observe(viewLifecycleOwner) {
            binding.rvMostLikedProducts.adapter = MostLikedAdapter(it) { product ->
                navigateToDetail(product)
            }
        }
    }

    private fun initRvCategory() {
        binding.rvHomeCategoryItem.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewModel.productList.observe(viewLifecycleOwner) {
            binding.rvHomeCategoryItem.adapter = CategoryAdapter(it) { product ->
                navigateToDetail(product)
            }
        }
    }

    private fun initRvSearch() {
        binding.rvSearch.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvSearch.adapter = SearchResultAdapter(productList) { product ->
            navigateToDetail(product)
        }
    }

    private fun navigateToDetail(it: Product) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
        findNavController().navigate(action)
    }

    private fun initListenerProductList() {
        viewModel.productResult.observe(viewLifecycleOwner) {
            productList.addAll(it)
            if (it.isNullOrEmpty()) {
                if (binding.svSearch.query.isNullOrEmpty()) {
                    hideNotFoundComponents()
                    showHomeComponents()
                } else {
                    showNotFoundComponents()
                    hideHomeComponents()
                }
            } else {
                hideNotFoundComponents()
                hideHomeComponents()
                binding.rvSearch.visibility = View.VISIBLE
            }
        }
    }

    private fun initSearchLogic() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                productList.clear()
                viewModel.searchFromFirebase(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                binding.rvSearch.visibility = View.GONE
                productList.clear()
                viewModel.searchFromFirebase(newText)
                return true
            }
        })
    }

    private fun showHomeComponents() {
        binding.apply {
            tvHomeTitle.visibility = View.VISIBLE
            cgHomeCategory.visibility = View.VISIBLE
            rvHomeCategoryItem.visibility = View.VISIBLE
            tvMostLikedProducts.visibility = View.VISIBLE
            rvMostLikedProducts.visibility = View.VISIBLE
            tvTop5.visibility = View.VISIBLE
        }
    }

    private fun hideHomeComponents() {
        binding.apply {
            tvHomeTitle.visibility = View.GONE
            cgHomeCategory.visibility = View.GONE
            rvHomeCategoryItem.visibility = View.GONE
            tvMostLikedProducts.visibility = View.GONE
            rvMostLikedProducts.visibility = View.GONE
            tvTop5.visibility = View.GONE
        }
    }

    private fun showNotFoundComponents() {
        binding.apply {
            tvTitleItemNotFound.visibility = View.VISIBLE
            tvDescItemNotFound.visibility = View.VISIBLE
            ivItemNotFound.visibility = View.VISIBLE
        }
    }

    private fun hideNotFoundComponents() {
        binding.apply {
            ivItemNotFound.visibility = View.GONE
            tvDescItemNotFound.visibility = View.GONE
            tvTitleItemNotFound.visibility = View.GONE
        }
    }
}