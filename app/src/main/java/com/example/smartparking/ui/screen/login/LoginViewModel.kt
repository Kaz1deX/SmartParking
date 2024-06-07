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

    fun auth(login: String, password: String, onResult: (header: String) -> Unit) {
        viewModelScope.launch {
            val header = repository.auth(login, password)
            onResult(header)

            if (header != "User not found" && header != "Invalid password" && header != "" && header != "No internet connection") {
                sharedPreferences.saveString(SharedPrefNames.TOKEN, header)
                sharedPreferences.saveString(SharedPrefNames.LOGIN, login)
            }
        }
    }

    fun checkToken(): Boolean {
        return sharedPreferences.getValueString(SharedPrefNames.TOKEN) != null
    }

    class LoginViewModelFactory(
        private val application: Application,
        private val repository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            LoginViewModel(application, repository) as T
    }
}