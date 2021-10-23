package com.noor.homework2_groupb.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.noor.homework2_groupb.data.model.Product

const val COLLECTION_PATH_PRODUCT = "product"
const val FIELD_NAME = "name"
const val FIELD_TYPE = "type"
const val PRODUCT_LIKE_COUNT = "likeCount"

class HomeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    init {
        getMostLikedProductsFromFirebase()
    }

    val showProgressBar: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
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
        showProgressBar.value = true
        db.collection(COLLECTION_PATH_PRODUCT).whereEqualTo(FIELD_NAME, query).get()
            .addOnSuccessListener {
                _productResult.value = it.toObjects(Product::class.java) as ArrayList<Product>
            }.addOnCompleteListener {
                showProgressBar.value = false
            }
    }

    private fun getMostLikedProductsFromFirebase() {
        db.collection(COLLECTION_PATH_PRODUCT)
            .orderBy(PRODUCT_LIKE_COUNT, Query.Direction.DESCENDING)
            .limit(5)
            .addSnapshotListener { value, _ ->
                if (value != null) {
                    _mostLikedProducts.value =
                        value.toObjects(Product::class.java) as ArrayList<Product>
                }
            }
    }

    fun getProductsByCategoryFromFirebase(category: String) {
        showProgressBar.value = true
        db.collection(COLLECTION_PATH_PRODUCT).whereEqualTo(FIELD_TYPE, category).get()
            .addOnSuccessListener {
                _productList.value = it.toObjects(Product::class.java) as ArrayList<Product>
            }.addOnCompleteListener {
                showProgressBar.value = false
            }
    }
}