package com.example.smartparking.ui.screen.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.sharedPref.SharedPrefNames
import com.example.smartparking.data.sharedPref.SharedPreferences
import kotlinx.coroutines.launch

class RegistrationViewModel(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {
    private val sharedPreferences = SharedPreferences(application)

    fun register(login: String, password: String, username: String, email: String, onResult: (header: String) -> Unit) {
        viewModelScope.launch {
            val header = repository.register(login, password, username, email)
            onResult(header)

            if (header != "User not found" && header != "Invalid password" && header != "") {
                sharedPreferences.saveString(SharedPrefNames.TOKEN, header)
                sharedPreferences.saveString(SharedPrefNames.LOGIN, login)
            }
        }
    }

    class RegistrationViewModelFactory(
        private val application: Application,
        private val repository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            RegistrationViewModel(application, repository) as T
    }
}