package com.noor.homework2_groupb.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val productList: ArrayList<Product>
) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    inner class CategoryHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context)
        return CategoryHolder(ItemCategoryBinding.inflate(view, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

}