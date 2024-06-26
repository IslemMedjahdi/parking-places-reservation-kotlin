package com.example.parking_places_reservation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.ParkingViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ParkingListOnMapScreen(
    navController: NavController,
    parkingsModel: ParkingViewModel){
    LaunchedEffect(true) {
        if(parkingsModel.parkings.value.isEmpty()){
            parkingsModel.getParkings()
        }
    }

    parkingsModel.loading.value.let {
        LoadingIndicator(it)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(28.0339, 1.6596), 6f
        )
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.15f))
    ) {
        GoogleMap(
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize(),
            onMapLongClick = {},
        ) {
            for (parking in parkingsModel.parkings.value) {
                val position = LatLng(parking.latitude, parking.longitude)
                Marker(
                    state = MarkerState(position = position),
                    title = parking.name,
                    snippet = parking.address,
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                )
                MarkerInfoWindowContent(
                    state = MarkerState(position = LatLng(parking.latitude, parking.longitude)),
                    onInfoWindowClick = {
                        navController.navigate(Router.ParkingDetailsById.createRoute(parking.id))
                    },
                    title = parking.name,
                    snippet = parking.address
                )
            }
        }
    }
}