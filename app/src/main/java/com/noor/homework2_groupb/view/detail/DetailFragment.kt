package com.noor.homework2_groupb.view.detail

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.inflate
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.ActivityMainBinding.inflate
import com.noor.homework2_groupb.databinding.FragmentDetailBinding
import kotlin.math.log


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val product = arguments?.getSerializable("product");
        var image = view.findViewById<ImageView>(R.id.image_view_product_picture)
        var productName = view.findViewById<TextView>(R.id.text_view_product_name)
        var productDescription = view.findViewById<TextView>(R.id.text_view_product_description)
        var productPrice = view.findViewById<TextView>(R.id.text_view_product_price)
        var productTitle = view.findViewById<TextView>(R.id.text_view_product_title)
        var btnFavorite = view.findViewById<ImageButton>(R.id.btn_favorite)
        var isFavorited  = true; // logic eklenecek

        if(isFavorited){
            btnFavorite.setImageResource(R.drawable.ic_favorite)
        }

        val product = Product(
            description = "The HUAWEI Band 6 has a 1.47-inch AMOLED FullView display, 148% larger2 viewable area and 64% low bezel screen-to-body ratio. Combine that with the high resolution 194x368 display with 282 PPI and you're looking at a truly impressive band.",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/bootcampprojectteamb.appspot.com/o/productImages%2Fp11.jpg?alt=media&token=3185c3fc-db6e-4608-9ee2-4713f849e6c9",
            name = "Huawei Band 6",
            price = 500,
            title = "New Band on Wrist",
            type = "Wearable"
        )
        productName.text=product.name
        productDescription.text = product.description
        productTitle.text = product.title
        productPrice.text = "$ " + product.price.toString()
        Glide.with(this).load(product.imageUrl).into(image)

        btnFavorite.setOnClickListener(View.OnClickListener {
            if (isFavorited){
                isFavorited = !isFavorited
                btnFavorite.setImageResource(R.drawable.ic_unfavorite)
            }
            else{
                isFavorited = !isFavorited
                btnFavorite.setImageResource(R.drawable.ic_favorite)
            }
        })
    }
}