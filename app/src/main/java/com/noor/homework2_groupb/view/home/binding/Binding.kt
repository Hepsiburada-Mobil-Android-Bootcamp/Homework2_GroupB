package com.noor.homework2_groupb.view.home.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.noor.homework2_groupb.R

class Binding {

    companion object {

        @JvmStatic
        @BindingAdapter("bindImageCircle")
        fun ImageView.bindImageCircle(url: String) {
            Glide.with(this)
                .load(url)
                .circleCrop()
                .into(this)
        }

        @JvmStatic
        @BindingAdapter("bindPrice")
        fun TextView.bindPrice(price: Int) {
            text = context.getString(R.string.price, price)
        }

        @JvmStatic
        @BindingAdapter("bindFavoriteCount")
        fun TextView.bindFavoriteCount(count: Int) {
            text = context.getString(R.string.favorite_count, count)
        }
    }
}