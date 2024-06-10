package com.example.parking_places_reservation.`view-models`

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parking_places_reservation.core.repositories.AuthRepository
import com.example.parking_places_reservation.core.retrofit.Endpoint
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository): ViewModel(){
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    var token = mutableStateOf("")
    var id = mutableStateOf("")

    var loading = mutableStateOf(false)
    var success = mutableStateOf(false)
    var error = mutableStateOf("")

    var isLoggedIn = mutableStateOf(false)



    fun login(){
        loading.value = true
        success.value = false
        error.value = ""

        viewModelScope.launch {
            val response = authRepository.login(
                Endpoint.LoginRequest(
                    email = email.value,
                    password = password.value
                )
            )
            if(response.isSuccessful){
                success.value = true
                authRepository.saveToken(response.body()!!.token)
                authRepository.saveId(response.body()!!.id)
                token.value = response.body()!!.token
                id.value = response.body()!!.id
                isLoggedIn.value = true
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

    fun loginWithGoogle(tokenId: String){
        loading.value = true
        success.value = false
        error.value = ""

        viewModelScope.launch {
            val response = authRepository.loginWithGoogle(
                Endpoint.LoginWithGoogleRequest(
                    tokenId = tokenId
                )
            )
            if(response.isSuccessful){
                success.value = true
                authRepository.saveToken(response.body()!!.token)
                authRepository.saveId(response.body()!!.id)
                token.value = response.body()!!.token
                id.value = response.body()!!.id
                isLoggedIn.value = true
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

    fun logout(){
        authRepository.clearToken()
        authRepository.clearId()
        token.value = ""
        id.value = ""
        isLoggedIn.value = false
        success.value = false
        error.value = ""
    }

    fun setup(){
        val token = authRepository.getToken();
        val id = authRepository.getId()
        this.token.value = token
        this.id.value = id

        isLoggedIn.value = token.isNotBlank() && id.isNotBlank()
    }

    class Factory(private val authRepository: AuthRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthViewModel(authRepository) as T
        }
    }

}