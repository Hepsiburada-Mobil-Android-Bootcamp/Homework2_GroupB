package com.noor.homework2_groupb.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.ItemMostLikedBinding

class MostLikedAdapter(
    private val productList: ArrayList<Product>
) : RecyclerView.Adapter<MostLikedAdapter.MostLikedViewHolder>() {

    inner class MostLikedViewHolder(private val binding: ItemMostLikedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostLikedViewHolder {
        val view = LayoutInflater.from(parent.context)
        return MostLikedViewHolder(ItemMostLikedBinding.inflate(view, parent, false))
    }

    override fun onBindViewHolder(holder: MostLikedViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

}