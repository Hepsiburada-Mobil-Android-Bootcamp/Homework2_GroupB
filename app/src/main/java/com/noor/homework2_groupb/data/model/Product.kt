package com.noor.homework2_groupb.data.model

import java.io.Serializable

data class Product(
    val name: String = "",
    val title: String = "",
    val type: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val price: Int = 0,
    var likeCount: Int = 0
): Serializable