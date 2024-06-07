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

    private var _availableSlots: MutableStateFlow<List<Pair<String, String>>> = MutableStateFlow(emptyList())
    val availableSlots = _availableSlots.asStateFlow()

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

    fun addBooking(parkingId: String, carNumber: String, checkIn: String, exit: String, amount: Int, parkingName: String) {
        viewModelScope.launch {
            val bookingReceive = BookingReceive(
                userLogin = sharedPreferences.getValueString(SharedPrefNames.LOGIN)!!,
                parkingId = parkingId,
                carNumber = carNumber,
                checkIn = checkIn,
                exit = exit,
                amount = amount,
                parkingName = parkingName
            )
            repository.addBooking(bookingReceive)
        }
    }

    fun deleteBooking(bookingId: String) {
        viewModelScope.launch {
            repository.deleteBooking(bookingId)
            getAllBooking()
        }
    }

    fun getAvailableSlots(parkingId: String, date: String) {
        viewModelScope.launch {
            val availableSlots = repository.getAvailableSlots(parkingId, date)
            _availableSlots.value = availableSlots
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