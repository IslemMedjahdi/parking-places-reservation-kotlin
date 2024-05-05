package com.example.parking_places_reservation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.parking_places_reservation.models.AuthViewModel
import com.example.parking_places_reservation.screens.router.Router

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Profile Screen", style = MaterialTheme.typography.headlineMedium)
    }
    LaunchedEffect(key1 = true) {
        if(!authViewModel.isLoggedIn.value){
            navController.navigate(Router.Login.createRoute(Router.Profile.route))
        }

    }
}