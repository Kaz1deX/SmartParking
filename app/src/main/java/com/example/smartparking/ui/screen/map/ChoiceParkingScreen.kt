package com.example.smartparking.ui.screen.map

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartparking.App
import com.example.smartparking.R
import com.example.smartparking.ui.theme.DividerGrey

@Composable
fun ChoiceParkingScreen(navController: NavHostController, context: Context) {

    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val viewModel: MapViewModel = viewModel(
        factory = MapViewModel.MapViewModelFactory(
            application,
            repository
        )
    )

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.my_ground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
        )
        Spacer(
            modifier = Modifier
                .padding(top = 15.dp)
        )
        Text(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            text = "name",
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier
                .padding(top = 40.dp)
        )

        Column (
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Адрес",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "address",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
            Divider(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                thickness = 1.dp,
                color = DividerGrey
            )

            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Описание",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "description",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
            Divider(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                thickness = 1.dp,
                color = DividerGrey
            )

            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Количество мест",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "total_places",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
            Divider(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                thickness = 1.dp,
                color = DividerGrey
            )

            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Цена в час",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "cost_per_hour",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
            Divider(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                thickness = 1.dp,
                color = DividerGrey
            )

            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Наличие зарядной станции",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "charging_station",
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
            Divider(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
                thickness = 1.dp,
                color = DividerGrey
            )
        }
        Box(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(
                    text = "Забронировать",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                )
            }
        }
    }
}

@Preview
@Composable
fun ChoiceParkingScreenPreview() {
    ChoiceParkingScreen(navController = rememberNavController(), context = LocalContext.current)
}