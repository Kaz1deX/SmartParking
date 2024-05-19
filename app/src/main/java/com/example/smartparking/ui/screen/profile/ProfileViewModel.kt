package com.example.smartparking.ui.screen.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.Car
import com.example.smartparking.data.model.CarReceive
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.room.database.MainDatabase
import com.example.smartparking.data.sharedPref.SharedPrefNames
import com.example.smartparking.data.sharedPref.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {
    private val sharedPreferences = SharedPreferences(application)

    private var _cars: MutableStateFlow<List<Car>> = MutableStateFlow(emptyList())
    val cars = _cars.asStateFlow()

    fun checkToken(): Boolean {
        return sharedPreferences.getValueString(SharedPrefNames.TOKEN) != null
    }

    fun clearSharedPref() {
        sharedPreferences.clearSharedPreference()
    }

    fun getCars(onResult: (cars: List<Car>) -> Unit) {
        viewModelScope.launch {
            val login = sharedPreferences.getValueString(SharedPrefNames.LOGIN)
            if (login != null) {
                val cars = repository.getCars(login)
                _cars.value = cars
                onResult(cars)
            } else {
                return@launch
            }
        }
    }

    fun addCar(number: String, model: String) {
        viewModelScope.launch {
            val login = sharedPreferences.getValueString(SharedPrefNames.LOGIN)
            val car = CarReceive(login!!, number, model)
            repository.addCar(car)
        }
    }

    class ProfileViewModelFactory(
        private val application: Application,
        private val repository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProfileViewModel(application, repository) as T
    }
}