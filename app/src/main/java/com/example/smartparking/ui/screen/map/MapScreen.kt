package com.example.smartparking.ui.screen.map

import android.app.Activity
import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartparking.App
import com.example.smartparking.R
import com.example.smartparking.data.model.Parking
import com.example.smartparking.ui.theme.DividerGrey

@Composable
fun MapScreen(navController: NavHostController, context: Context) {

    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val viewModel: MapViewModel = viewModel(
        factory = MapViewModel.MapViewModelFactory(
            application,
            repository
        )
    )
    val parkings = viewModel.parking.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getParking()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.
            padding(top = 35.dp),
            text = stringResource(id = R.string.mapScreen),
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .padding(top = 50.dp)
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(
                parkings.value
            ) {index, item ->
                MapItem(item, navController)
                if(index != (parkings.value.size - 1)) {
                    Divider(modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
                        thickness = 1.dp,
                        color = DividerGrey
                    )
                }
            }
        }
    }
}

@Composable
fun MapItem(parking: Parking, navController: NavHostController) {
    val isButtonEnabled = remember { mutableStateOf(false) }
    val buttonColors = if (isButtonEnabled.value) Blue else Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(50.dp)
            .clickable {
                navController.navigate(Screen.ChoiceParkingScreen.route)
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.map_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(42.dp)
            )
            Column {
                Text(
                    text = parking.name!!,
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 15.dp)
                )
                Text(
                    text = parking.address,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 5.dp)
                )
            }
        }
        IconButton(
            onClick = {
                isButtonEnabled.value = !isButtonEnabled.value
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(
//                imageVector = Icons.Default.Favorite,
                imageVector = if (isButtonEnabled.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favourite",
                tint = buttonColors
            )
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(navController = rememberNavController(), context = LocalContext.current)
}