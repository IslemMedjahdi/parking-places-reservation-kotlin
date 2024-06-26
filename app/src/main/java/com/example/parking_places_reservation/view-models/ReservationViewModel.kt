package com.example.parking_places_reservation.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parking_places_reservation.core.repositories.AuthRepository
import com.example.parking_places_reservation.core.retrofit.Endpoint
import com.example.parking_places_reservation.core.repositories.ReservationRepository
import com.example.parking_places_reservation.core.room.ReservationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReservationViewModel(private val reservationRepository: ReservationRepository ,private val authRepository: AuthRepository) : ViewModel() {

    var startDate = mutableStateOf("")
    var endDate = mutableStateOf("")
    var startTime = mutableStateOf("")
    var endTime = mutableStateOf("")

    var myReservations = mutableStateOf(listOf<ReservationEntity>())
    var selectedReservation = mutableStateOf<ReservationEntity?>(null)


    var loading = mutableStateOf(false)
    var success = mutableStateOf(false)
    var error = mutableStateOf("")

    fun getReservationFromLocal(id : String){
        viewModelScope.launch {
            val reservations = withContext(Dispatchers.IO){
                reservationRepository.getReservationsLocal(id)
            }
            myReservations.value = reservations
        }
    }

    fun getReservationByIdLocal(id: String){
       viewModelScope.launch {
            val reservation = withContext(Dispatchers.IO){
                reservationRepository.getReservationByIdLocal(id)
            }
            selectedReservation.value = reservation
        }
    }

    fun deleteAllReservationsLocal(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                reservationRepository.deleteAllReservationsLocal()
            }
        }
    }

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
                    parkingId = parkingId ,
                    driverId = authRepository.getId()
                )
            )
            if(response.isSuccessful){
                success.value = true
                response.body().let {
                    withContext(Dispatchers.IO) {
                        reservationRepository.saveReservationLocal(
                            ReservationEntity(
                                id = it!!.id,
                                parkingId = it.parkingId,
                                endTime = it.endDate,
                                startTime = it.startDate,
                                parkingAddress = it.parkingAddress,
                                parkingName = it.parkingName,
                                parkingPhotoUrl = it.parkingPhotoUrl,
                                userId = it.userId
                            )
                        )
                    }
                }
                startDate.value = ""
                endDate.value = ""
                startTime.value = ""
                endTime.value = ""

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

    class Factory(private val reservationRepository: ReservationRepository ,  private val authRepository: AuthRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationViewModel(reservationRepository , authRepository) as T
        }
    }

}