package com.example.parking_places_reservation.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parking_places_reservation.core.entities.Parking
import com.example.parking_places_reservation.core.retrofit.ParkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class ParkingByIdViewModel(private val parkingRepository: ParkingRepository): ViewModel() {
    var selectedParking = mutableStateOf<Parking?>(null)
    fun getParkingById(id: String) {
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val response = parkingRepository.getParkingById(id)
                if(response.isSuccessful){
                    selectedParking.value = response.body()
                }
            }
        }
    }

    class Factory(private val parkingRepository: ParkingRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingByIdViewModel(parkingRepository) as T
        }
    }

}