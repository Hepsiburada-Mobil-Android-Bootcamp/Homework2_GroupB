package com.noor.homework2_groupb.data.local

import android.content.Context
import android.content.SharedPreferences
import com.noor.homework2_groupb.data.model.User

class SharedPrefManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun setOnboardSeen(){
        sharedPreferences.edit().putBoolean("ONBOARD",true).apply()
    }

    fun isOnboardSeen(): Boolean {
        return sharedPreferences.getBoolean("ONBOARD", false)
    }
}