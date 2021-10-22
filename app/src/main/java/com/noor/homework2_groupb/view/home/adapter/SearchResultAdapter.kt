package com.noor.homework2_groupb.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.ItemSearchResultBinding

class SearchResultAdapter(
    private val resultArray: ArrayList<Product>
) : RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(resultArray[position])
    }

    override fun getItemCount(): Int = resultArray.size
}