package com.noor.homework2_groupb.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.Product

class HomeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    init {
        getProductsByCategoryFromFirebase("wearable")
        getMostLikedProductsFromFirebase()
    }

    private val _productList by lazy {
        MutableLiveData<ArrayList<Product>>()
    }
    var productList: LiveData<ArrayList<Product>> = _productList

    private val _mostLikedProducts by lazy {
        MutableLiveData<ArrayList<Product>>()
    }
    var mostLikedProducts: LiveData<ArrayList<Product>> = _mostLikedProducts

    private val _productResult: MutableLiveData<ArrayList<Product>> by lazy {
        MutableLiveData<ArrayList<Product>>()
    }
    var productResult: MutableLiveData<ArrayList<Product>> = _productResult

    fun searchFromFirebase(query: String?) {
        db.collection("productDeneme").whereEqualTo("productName", query).get()
            .addOnSuccessListener {
                _productResult.value = it.toObjects(Product::class.java) as ArrayList<Product>
            }
    }

    private fun getMostLikedProductsFromFirebase() {
        db.collection("productDeneme").addSnapshotListener { value, _ ->
            if (value != null) {
                _mostLikedProducts.value =
                    value.toObjects(Product::class.java) as ArrayList<Product>
            }
        }
    }

    fun getProductsByCategoryFromFirebase(category: String) {
        db.collection("productDeneme").whereEqualTo("productType", category).get()
            .addOnSuccessListener {
                _productList.value = it.toObjects(Product::class.java) as ArrayList<Product>
            }
    }
}