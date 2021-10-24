package com.noor.homework2_groupb.view.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.Product

const val COLLECTION_FAVORITES = "favorites"
const val COLLECTION_PRODUCT = "product"

class FavoritesViewModel: ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser!!.uid

    val _productList by lazy {
        MutableLiveData<ArrayList<Product>>()
    }

    var productList: ArrayList<Product> = arrayListOf()

    var productSample: Product = Product()
    var productIdList: HashMap<String, ArrayList<String>> = HashMap()

    fun getFavoritesFromFirebase() {
        db.collection(COLLECTION_FAVORITES).document(currentUser).get().addOnSuccessListener { it ->
            productIdList = it.data as HashMap<String, ArrayList<String>>
            Log.d("HASHMAP", productIdList.toString())
            productIdList["name"]?.forEach { productId ->
                getFavoriteAll(productId)
            }
        }.addOnCompleteListener {
            _productList.value = productList
        }
    }

    private fun getFavoriteAll(productId: String) {
        db.collection(COLLECTION_PRODUCT).document(productId).get().addOnSuccessListener {
            productList.add(it.toObject(Product::class.java) as Product)
            Log.d("PRODUCTT", it["name"].toString())
        }
    }

}