package com.example.parking_places_reservation.`view-models`

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parking_places_reservation.core.entities.Parking
import com.example.parking_places_reservation.core.repositories.ParkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParkingViewModel(private val parkingRepository: ParkingRepository): ViewModel() {

    var parkings = mutableStateOf(listOf<Parking>())

    var loading = mutableStateOf(true)

    fun getParkings() {
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val response = parkingRepository.getParkings()
                if(response.isSuccessful){
                    parkings.value = response.body() as MutableList<Parking>
                }else{
                    parkings.value = emptyList()
                }
                loading.value = false
            }
        }
    }



    class Factory(private val parkingService: ParkingRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingViewModel(parkingService) as T
        }
    }
    
}