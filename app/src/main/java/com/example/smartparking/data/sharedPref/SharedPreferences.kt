package com.example.smartparking.data.sharedPref

import android.content.Context

class SharedPreferences(context: Context) {
    private val sharedPref = context.getSharedPreferences(SharedPrefNames.USER_INFO, Context.MODE_PRIVATE)


    fun saveString(keyName: String, value: String) {
        val editor = sharedPref?.edit()
        if (editor != null) {
            editor.putString(keyName, value)
            editor.apply()
        }
    }


    fun saveInt(keyName: String, value: Int) {
        val editor = sharedPref?.edit()
        if (editor != null) {
            editor.putInt(keyName, value)
            editor.apply()
        }
    }

    fun getValueString(keyName: String) = sharedPref?.getString(keyName, null)

    fun getValueInt(keyName: String) = sharedPref?.getInt(keyName, 0)

    fun clearSharedPreference() {
        val editor = sharedPref?.edit()
        if (editor != null) {
            editor.clear()
            editor.apply()
        }
    }
}