package com.example.smartparking.ui.screen.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.sharedPref.SharedPrefNames
import com.example.smartparking.data.sharedPref.SharedPreferences
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {
    private val sharedPreferences = SharedPreferences(application)

    fun auth(login: String, password: String) {
        viewModelScope.launch {
            val header = repository.auth(login, password)

            if (header != "User not found" && header != "Invalid password" && header != "") {
                sharedPreferences.saveString(SharedPrefNames.TOKEN, header)
            }
        }
    }

    class LoginViewModelFactory(
        private val application: Application,
        private val repository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            LoginViewModel(application, repository) as T
    }
}