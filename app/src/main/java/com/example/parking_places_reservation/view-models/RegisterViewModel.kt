package com.example.parking_places_reservation.`view-models`

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parking_places_reservation.core.retrofit.AuthRepository
import com.example.parking_places_reservation.core.retrofit.Endpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var fullName = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    var loading = mutableStateOf(false)
    var success = mutableStateOf(false)
    var error = mutableStateOf("")

    fun register() {
        loading.value = true
        success.value = false
        error.value = ""
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = authRepository.register(
                    Endpoint.RegisterRequest(
                        email = email.value,
                        password = password.value,
                        fullName = fullName.value
                    )
                )
                if (response.isSuccessful) {
                    success.value = true
                } else {
                    error.value = response.message()
                    if (error.value.isBlank()) {
                        error.value = response.code().toString()
                    }
                }
                loading.value = false
            }
        }
    }

    class Factory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(authRepository) as T
        }
    }

}