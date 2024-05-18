package com.example.smartparking.ui.screen.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.room.database.MainDatabase
import com.example.smartparking.data.sharedPref.SharedPrefNames
import com.example.smartparking.data.sharedPref.SharedPreferences

class FavouriteViewModel(
    application: Application,
    mainDatabase: MainDatabase
) : AndroidViewModel(application) {

    class FavouriteViewModelFactory(
        private val application: Application,
        private val mainDatabase: MainDatabase
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            FavouriteViewModel(application, mainDatabase) as T
    }
}