package com.example.parking_places_reservation.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parking_places_reservation.core.retrofit.AuthRepository
import com.example.parking_places_reservation.core.retrofit.Endpoint
import com.example.parking_places_reservation.core.retrofit.ReservationRepository
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import kotlinx.coroutines.launch


class ReserveViewModel(private val reservationRepository: ReservationRepository) : ViewModel() {

    var startDate = mutableStateOf("")
    var endDate = mutableStateOf("")

    var loading = mutableStateOf(false)
    var success = mutableStateOf(false)
    var error = mutableStateOf("")

    fun reserve(parkingId: String){
        loading.value = true
        success.value = false
        error.value = ""

        viewModelScope.launch {
            val response = reservationRepository.reserve(
                Endpoint.ReserveRequest(
                    startDate = startDate.value,
                    endDate = endDate.value,
                    parkingId = parkingId
                )
            )
            if(response.isSuccessful){
                success.value = true
                response.body().let {
                    // Do something with the response
                    // save it in local
                }
            }
            else{
                error.value = response.message()
                if(error.value.isBlank()){
                    error.value = response.code().toString()
                }
            }
            loading.value = false
        }
    }

    class Factory(private val reservationRepository: ReservationRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReserveViewModel(reservationRepository) as T
        }
    }

}