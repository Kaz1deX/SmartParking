package com.example.smartparking.ui.screen.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.Booking
import com.example.smartparking.data.model.BookingReceive
import com.example.smartparking.data.model.Car
import com.example.smartparking.data.model.CarReceive
import com.example.smartparking.data.repositories.MainRepository
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

    private var _booking: MutableStateFlow<List<Booking>> = MutableStateFlow(emptyList())
    val booking = _booking.asStateFlow()

    private var _login: MutableStateFlow<String> = MutableStateFlow("")
    val login = _login.asStateFlow()

    fun checkToken(): Boolean {
        return sharedPreferences.getValueString(SharedPrefNames.TOKEN) != null
    }

    fun getUserName(onResult: (userName: String) -> Unit) {
        viewModelScope.launch {
            val login = sharedPreferences.getValueString(SharedPrefNames.LOGIN)
            if (login != null) {
                val user = repository.getUserByLogin(login)
                _login.value = user.username
                sharedPreferences.saveString(SharedPrefNames.USER_NAME, user.username)
                onResult(user.username)
            } else {
                return@launch
            }
        }
    }

    fun getUserNameFromSharedPref(): String {
        return sharedPreferences.getValueString(SharedPrefNames.USER_NAME)!!
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

    fun deleteTables() {
        viewModelScope.launch {
            repository.deleteCarsTable()
            repository.deleteFavouriteTable()
        }
    }

    fun addCar(number: String, model: String) {
        viewModelScope.launch {
            val login = sharedPreferences.getValueString(SharedPrefNames.LOGIN)
            val car = CarReceive(login!!, number, model)
            repository.addCar(car)
            getCars {}
        }
    }

    fun deleteCar(number: String) {
        viewModelScope.launch {
            val login = sharedPreferences.getValueString(SharedPrefNames.LOGIN)
            repository.deleteCar(login!!, number)
            getCars {}
        }
    }

    fun getAllBooking() {
        viewModelScope.launch {
            val booking = repository.getAllBooking(login = sharedPreferences.getValueString(SharedPrefNames.LOGIN)!!)
            _booking.value = booking
        }
    }

    fun deleteBooking(bookingId: String) {
        viewModelScope.launch {
            repository.deleteBooking(bookingId)
            getAllBooking()
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