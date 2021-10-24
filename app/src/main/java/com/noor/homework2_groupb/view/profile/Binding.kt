package com.noor.homework2_groupb.view.profile

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Binding {

    companion object {
        @JvmStatic
        @BindingAdapter("bindProfileImage")
        fun ImageView.bindProfileImage(src: String?) {
            if (src != null) {
                Glide.with(this.context).load(src).into(this)
            }
        }
    }

}