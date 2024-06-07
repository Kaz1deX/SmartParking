package com.example.smartparking.ui.screen.map

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.BookingReceive
import com.example.smartparking.data.model.Car
import com.example.smartparking.data.model.Parking
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.sharedPref.SharedPrefNames
import com.example.smartparking.data.sharedPref.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapViewModel(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {
    private val sharedPreferences = SharedPreferences(application)

    private var _parking: MutableStateFlow<List<Parking>> = MutableStateFlow(emptyList())
    val parking = _parking.asStateFlow()

    private var _parkingOne: MutableStateFlow<Parking?> = MutableStateFlow(null)
    val parkingOne = _parkingOne.asStateFlow()

    private var _availableSlots: MutableStateFlow<List<Pair<String, String>>> = MutableStateFlow(emptyList())
    val availableSlots = _availableSlots.asStateFlow()

    private var _amount: MutableStateFlow<Int> = MutableStateFlow(0)
    var amount = _amount.asStateFlow()

    private val _selectedIntervals = mutableListOf<Pair<String, String>>()
    private val _bookingIntervals = MutableStateFlow<List<BookingInterval>>(emptyList())
    val bookingIntervals: StateFlow<List<BookingInterval>> = _bookingIntervals.asStateFlow()

    fun getParking() {
        viewModelScope.launch {
            val parking = repository.getParking()
            _parking.value = parking
        }
    }

    fun getParkingById(parkingId: String, onResult: (parking: Parking) -> Unit) {
        viewModelScope.launch {
            val parking = repository.getParkingById(parkingId)
            _parkingOne.value = parking
            onResult(parking)
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

    fun createBookings(parkingId: String, carNumber: String, parkingName: String, date: String) {
        viewModelScope.launch {
            _bookingIntervals.value.forEach {
                val checkIn = "$date ${it.checkIn}"
                val exit = "$date ${it.exit}"
                addBooking(parkingId, carNumber, checkIn, exit, it.amount, parkingName)
            }
        }
    }

    fun getAvailableSlots(parkingId: String, date: String, onResult: (availableSlots: List<Pair<String, String>>) -> Unit) {
        viewModelScope.launch {
            val availableSlots = repository.getAvailableSlots(parkingId, date)
            _availableSlots.value = availableSlots
            onResult(availableSlots)
        }
    }

    fun incrementAmount() {
        _amount.value += 1
    }

    fun decrementAmount() {
        _amount.value -= 1
    }

    fun onIntervalSelected(interval: Pair<String, String>, isSelected: Boolean, costPerHour: Int) {
        if (isSelected) {
            _selectedIntervals.add(interval)
        } else {
            _selectedIntervals.remove(interval)
        }
        updateBookingIntervals(costPerHour)
    }

    private fun updateBookingIntervals(costPerHour: Int) {
        val sortedIntervals = _selectedIntervals.sortedBy { it.first }
        val intervals = mutableListOf<BookingInterval>()

        var currentStart = ""
        var currentEnd = ""
        var currentAmount = costPerHour

        for (interval in sortedIntervals) {
            if (currentStart.isEmpty()) {
                currentStart = interval.first
                currentEnd = interval.second
            } else {
                if (interval.first == currentEnd) {
                    currentEnd = interval.second
                    currentAmount += costPerHour
                } else {
                    intervals.add(BookingInterval(currentStart, currentEnd, currentAmount))
                    currentStart = interval.first
                    currentEnd = interval.second
                    currentAmount = costPerHour
                }
            }
        }

        if (currentStart.isNotEmpty()) {
            intervals.add(BookingInterval(currentStart, currentEnd, currentAmount))
        }

        _bookingIntervals.value = intervals
    }

    class MapViewModelFactory(
        private val application: Application,
        private val repository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MapViewModel(application, repository) as T
    }
}

data class BookingInterval(
    val checkIn: String,
    val exit: String,
    var amount: Int
)