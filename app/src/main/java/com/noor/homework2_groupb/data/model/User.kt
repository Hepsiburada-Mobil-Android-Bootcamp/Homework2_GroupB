package com.noor.homework2_groupb.data.model

import android.net.Uri

data class User(
    val eMail: String? = null,
    val password: String? = null,
    val username: String? = null,
    val img: Uri? = null,
    val name:String? = null,
    val surname: String? = null,
    val adress: String? = null,
    val phoneNumber: Int? = null
)
