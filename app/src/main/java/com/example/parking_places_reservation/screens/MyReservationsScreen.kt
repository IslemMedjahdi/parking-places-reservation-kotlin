package com.example.parking_places_reservation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.parking_places_reservation.core.room.ReservationEntity
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import com.example.parking_places_reservation.view.ReservationViewModel

@Composable
fun MyReservationsScreen(navController: NavController,authViewModel: AuthViewModel,reservationViewModel : ReservationViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "My Reservations List:",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(900)
            )
        }
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = reservationViewModel.myReservations.value,
                key = { reservation -> reservation.id.toString() }
            ) { reservation ->
                ReservationListItem(reservation, navController = navController)
            }
        }
    }

    LaunchedEffect(key1 = authViewModel.isLoggedIn.value) {
        if(!authViewModel.isLoggedIn.value){
            navController.navigate(Router.Login.createRoute(Router.MyReservations.route))
        }
    }

    LaunchedEffect(key1 = reservationViewModel.myReservations.value) {
        reservationViewModel.getReservationFromLocal(authViewModel.id.value.toString())
    }
}



@Composable
fun ReservationListItem(reservation: ReservationEntity , navController: NavController) {
    val startTimeGreaterThanCurrent = isDateStringGreaterThanCurrent(reservation.startTime)
    val endTimeGreaterThanCurrent = isDateStringGreaterThanCurrent(reservation.endTime)

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
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = reservation.parkingName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Address:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = reservation.parkingAddress,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Parking Session",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
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
                        text = reservation.startTime,
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
                        text = reservation.endTime,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ID",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = reservation.id,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = { navController.navigate(
                        Router.ReservationDetails.createRoute(reservation.id)
                    ) },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "More Info",
                        color = Color.White
                    )
                }
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
    }
}