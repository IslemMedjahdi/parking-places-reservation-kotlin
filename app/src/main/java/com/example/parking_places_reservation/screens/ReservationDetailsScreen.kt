package com.example.parking_places_reservation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import com.example.parking_places_reservation.view.ReservationViewModel
import com.lightspark.composeqr.QrCodeView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun isDateStringGreaterThanCurrent(dateString: String): Boolean {
    return try{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val currentDate = Calendar.getInstance().time
        val date = dateFormat.parse(dateString)
        if (date != null) {
            date > currentDate
        }else{
            false
        }
    }catch (e : Exception){
        false
    }
}

@Composable
fun ReservationDetailsScreen(navController: NavController,reservationId: String,authViewModel: AuthViewModel , reservationViewModel: ReservationViewModel) {

    val startTimeGreaterThanCurrent = isDateStringGreaterThanCurrent("${reservationViewModel.selectedReservation.value?.startTime}")
    val endTimeGreaterThanCurrent = isDateStringGreaterThanCurrent("${reservationViewModel.selectedReservation.value?.endTime}")

    val status = if (startTimeGreaterThanCurrent && endTimeGreaterThanCurrent) {
        "Not Started Yet"
    } else if (endTimeGreaterThanCurrent) {
        "Pending"
    } else {
        "Ended"
    }
    val colorByStatus = when (status) {
        "Not Started Yet" -> Color(0xFF64B5F6) // Light Blue
        "Pending" -> Color(0xFFFFD54F) // Amber
        "Ended" -> Color(0xFFEF5350) // Red
        else -> Color.Black // Black as default
    }

    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${reservationViewModel.selectedReservation.value?.parkingName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(text = "Scan The QR Code to Validate Your Reservation", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold,color=MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(16.dp))
            QrCodeView(
                data = reservationId,
                modifier = Modifier.size(300.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Unique ID: ${reservationViewModel.selectedReservation.value?.id}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Reservation Details",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Start Time:",
                                fontSize = 14.sp
                            )
                            Text(
                                text = "${reservationViewModel.selectedReservation.value?.startTime}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column {
                            Text(
                                text = "End Time:",
                                fontSize = 14.sp
                            )
                            Text(
                                text ="${reservationViewModel.selectedReservation.value?.endTime}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Status",
                        fontSize = 14.sp
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(colorByStatus)
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                    ){
                    Text(
                        text = status,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Don't know the route? See on Map",
                fontSize = 14.sp,
                color = Color.Blue,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(Router.ParkingOnMap.createRoute("${reservationViewModel.selectedReservation.value?.parkingId}"))
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(Router.ParkingDetailsById.createRoute("${reservationViewModel.selectedReservation.value?.parkingId}")) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "View Parking Details", fontSize = 16.sp)
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