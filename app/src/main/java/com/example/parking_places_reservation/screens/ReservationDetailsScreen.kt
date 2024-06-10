package com.example.parking_places_reservation.screens

import android.util.DisplayMetrics
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import com.example.parking_places_reservation.`view-models`.ParkingViewModel
import com.example.parking_places_reservation.view.ReservationViewModel
import com.lightspark.composeqr.QrCodeView

@Composable
fun ReservationDetailsScreen(navController: NavController,reservationId: String,authViewModel: AuthViewModel , reservationViewModel: ReservationViewModel) {
    val context = LocalContext.current
    val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
    val dpHeight = displayMetrics.heightPixels / displayMetrics.density
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            model = reservationViewModel.selectedReservation.value?.parkingPhotoUrl ?: "",
            contentDescription = null,
            modifier = Modifier
                .size(dpWidth.dp, dpHeight.dp / 3)
                .border(
                    shape = RoundedCornerShape(8.dp),
                    width = 1.dp,
                    color = Color.Gray
                )
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "${reservationViewModel.selectedReservation.value?.parkingName}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight(900),
            modifier = Modifier.padding(10.dp)
        )
        Text(text = "Scan The QR Code to Validate Your Reservation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(16.dp))
        QrCodeView(
            data = reservationId,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = { navController.navigate(Router.ParkingOnMap.createRoute(reservationViewModel.selectedReservation.value!!.parkingId))
            }
        ) {
            Text(
                text = "See on Map ${reservationViewModel.selectedReservation.value?.parkingId}",
                color = Color.White,
                modifier = Modifier.padding(7.dp)
            )
        }
    }


    LaunchedEffect(key1 = authViewModel.isLoggedIn.value) {
        if(!authViewModel.isLoggedIn.value){
            navController.navigate(Router.Login.createRoute(Router.ReservationDetails.createRoute(reservationId)))
        }
    }
    LaunchedEffect(key1 = reservationId) {
        reservationViewModel.getReservationByIdLocal(reservationId)
    }
}