package com.example.smartparking.ui.screen.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.Parking
import com.example.smartparking.data.repositories.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapViewModel(
    application: Application,
    private val repository: MainRepository
) : AndroidViewModel(application) {

    private var _parking: MutableStateFlow<List<Parking>> = MutableStateFlow(emptyList())
    val parking = _parking.asStateFlow()

    fun getParking() {
        viewModelScope.launch {
            val parking = repository.getParking()
            _parking.value = parking
        }
    }

    class MapViewModelFactory(
        private val application: Application,
        private val repository: MainRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MapViewModel(application, repository) as T
    }
}