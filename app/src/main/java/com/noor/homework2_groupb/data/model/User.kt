package com.noor.homework2_groupb.data.model

data class User(
    val username: String? = "",
    val name:String? = "",
    val surname: String? = "",
    val address: String? = "",
    val phoneNumber: Int? = 0,
    var isUserInformationSeen: Boolean= false,
    val img: String? = ""
){
    fun changeSeen() {
        isUserInformationSeen = true
    }
}

