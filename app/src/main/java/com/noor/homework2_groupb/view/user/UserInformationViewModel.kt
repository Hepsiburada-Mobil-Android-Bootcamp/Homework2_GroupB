package com.noor.homework2_groupb.view.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.noor.homework2_groupb.data.model.User

class UserInformationViewModel: ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val currentUser= FirebaseAuth.getInstance().currentUser!!.uid


    fun editProfile(username:String,name:String,surname:String,address:String,phoneNumber:Int,isUserInformationSeen:Boolean){
        val user = User(username,name,surname,address,phoneNumber,isUserInformationSeen)
        user.changeSeen()
        db.collection("users").document(currentUser).set(user)
    }



}