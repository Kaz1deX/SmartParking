package com.example.smartparking.ui.screen.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.sharedPref.SharedPrefNames
import com.example.smartparking.data.sharedPref.SharedPreferences

class ProfileViewModel(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {
    private val sharedPreferences = SharedPreferences(application)


    fun checkToken(): Boolean {
        return sharedPreferences.getValueString(SharedPrefNames.TOKEN) != null
    }

    fun clearSharedPref() {
        sharedPreferences.clearSharedPreference()
    }

    class ProfileViewModelFactory(
        private val application: Application,
        private val repository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProfileViewModel(application, repository) as T
    }
}