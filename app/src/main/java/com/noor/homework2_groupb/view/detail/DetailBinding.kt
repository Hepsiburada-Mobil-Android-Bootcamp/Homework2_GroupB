package com.noor.homework2_groupb.view.detail

import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.noor.homework2_groupb.R

class DetailBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("bindImage")
        fun ImageView.bindImage(imageUrl: String) {
            Glide.with(this)
                .load(imageUrl)
                .into(this)
        }

        @JvmStatic
        @BindingAdapter("bindFavorite")
        fun ImageButton.bindFavorite(isLiked: Boolean) {
            return when (isLiked) {
                true -> setColorFilter(ContextCompat.getColor(this.context, R.color.purple))
                false -> setColorFilter(ContextCompat.getColor(this.context, R.color.white))
            }
        }

    }

}