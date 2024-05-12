package com.example.parking_places_reservation.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parking_places_reservation.core.retrofit.Endpoint
import com.example.parking_places_reservation.core.repositories.ReservationRepository
import com.example.parking_places_reservation.core.room.ReservationEntity
import kotlinx.coroutines.launch


class ReserveViewModel(private val reservationRepository: ReservationRepository) : ViewModel() {

    var startDate = mutableStateOf("")
    var endDate = mutableStateOf("")
    var startTime = mutableStateOf("")
    var endTime = mutableStateOf("")


    var loading = mutableStateOf(false)
    var success = mutableStateOf(false)
    var error = mutableStateOf("")

    fun createReservation(parkingId: String){
        loading.value = true
        success.value = false
        error.value = ""

        val startDateTime = "${startDate.value} ${startTime.value}"
        val endDateTime = "${endDate.value} ${endTime.value}"

        viewModelScope.launch {
            val response = reservationRepository.reserve(
                Endpoint.ReserveRequest(
                    startDate = startDateTime,
                    endDate = endDateTime,
                    parkingId = parkingId
                )
            )
            if(response.isSuccessful){
                success.value = true
                response.body().let {
                    reservationRepository.saveReservationLocal(ReservationEntity(
                        id = it!!.id,
                        parkingId = it.parkingId,
                        endTime = it.endDate,
                        startTime = it.startDate,
                        parkingAddress = it.parkingAddress,
                        parkingName = it.parkingName,
                        parkingPhotoUrl = it.parkingPhotoUrl
                    ))
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