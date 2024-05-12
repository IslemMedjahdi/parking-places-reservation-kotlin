package com.example.parking_places_reservation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.parking_places_reservation.view.ReserveViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking_places_reservation.screens.router.Router
import com.example.parking_places_reservation.`view-models`.AuthViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection
import kotlinx.coroutines.launch
import okhttp3.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReservationScreen(navController: NavController,parkingId: String,reserveViewModel: ReserveViewModel,authViewModel: AuthViewModel) {
    val startDateState = rememberSheetState()
    val startTimeState = rememberSheetState()
    val endDateState = rememberSheetState()
    val endTimeState = rememberSheetState()

    ClockDialog(state = startTimeState, selection =
    ClockSelection.HoursMinutes{
            hours, minutes -> reserveViewModel.startTime.value= "$hours:$minutes"
    }
    )

    CalendarDialog(
        state = startDateState,
        selection = CalendarSelection.Date{
                date -> reserveViewModel.startDate.value = date.toString()
        }

    )

    ClockDialog(state = endTimeState, selection =
    ClockSelection.HoursMinutes{
            hours, minutes -> reserveViewModel.endTime.value= "$hours:$minutes"
    }
    )

    CalendarDialog(
        state = endDateState,
        selection = CalendarSelection.Date{
                date -> reserveViewModel.endDate.value = date.toString()
        }

    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "Reservation creation",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,

            )
            Text(
                text = "Enter start and end dates to create a reservation",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp, color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        startDateState.show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(15.dp)),

                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(
                    text = reserveViewModel.startDate.value,
                    modifier = Modifier.padding(7.dp),
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        startTimeState.show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(15.dp)),

                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = "Select start time",modifier = Modifier.padding(7.dp),color = Color.Black)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        endDateState.show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(15.dp)),

                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = "Select end date",modifier = Modifier.padding(7.dp),color = Color.Black)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        endTimeState.show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(15.dp)),

                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = "Select end time",modifier = Modifier.padding(7.dp),color = Color.Black)
            }
            Spacer(modifier = Modifier.height(16.dp))


            Button(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    reserveViewModel.createReservation(parkingId)
                }
            ) {
                Text(
                    text = "Create reservation",
                    color = Color.White,
                    modifier = Modifier.padding(7.dp)
                )
                if (reserveViewModel.loading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(7.dp),
                        color = Color.White
                    )
                }

            }
        }
    }
    LaunchedEffect(key1 = authViewModel.isLoggedIn.value) {
        if(!authViewModel.isLoggedIn.value){
            navController.navigate(Router.Login.createRoute((Router.CreateReservation.createRoute(parkingId))))
        }
    }
}