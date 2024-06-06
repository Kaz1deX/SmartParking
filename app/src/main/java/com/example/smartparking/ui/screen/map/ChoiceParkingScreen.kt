package com.example.smartparking.ui.screen.map

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.smartparking.ui.theme.Blue
import com.example.smartparking.ui.theme.DividerGrey

@OptIn(ExperimentalLayoutApi::class)
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

    val times = listOf(
        "09:00-10:00",
        "10:00-11:00",
        "11:00-12:00",
        "12:00-13:00",
        "13:00-14:00",
        "14:00-15:00",
        "15:00-16:00",
        "16:00-17:00",
        "17:00-18:00",
        "18:00-19:00",
        "20:00-21:00"
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
                .height(150.dp)
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
                    .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 10.dp),
                thickness = 1.dp,
                color = DividerGrey
            )
        }

        FlowRow(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 15.dp)
        ) {
            times.forEach {
                ActionChips(item = it)
            }
        }

        Box(
            modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 24.dp),
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

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ActionChips(item: String) {
//    Chip(
//        modifier = Modifier
//            .padding(8.dp),
//        onClick = {
//
//        }
//    ) {
//        Text(text = item)
//    }
    var isSelectedChip by remember { mutableStateOf(false) }
    FilterChip(
        selected = isSelectedChip,
        modifier = Modifier
            .height(60.dp)
            .padding(5.dp),
        onClick = {
            isSelectedChip = !isSelectedChip
        },
        label = {
            Text(item)
        },
        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = Blue, selectedLabelColor = Color.White)
    )
}

@Preview
@Composable
fun ChoiceParkingScreenPreview() {
    ChoiceParkingScreen(navController = rememberNavController(), context = LocalContext.current)
}