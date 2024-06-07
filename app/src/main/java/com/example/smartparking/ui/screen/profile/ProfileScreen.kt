package com.example.smartparking.ui.screen.profile

import android.app.Activity
import android.content.Context
import android.util.Log
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.smartparking.App
import com.example.smartparking.R
import com.example.smartparking.data.room.database.MainDatabase
import com.example.smartparking.navigation.Screen
import com.example.smartparking.ui.theme.Blue
import com.example.smartparking.ui.theme.DividerGrey

@Composable
fun ProfileScreen(navController: NavHostController, context: Context) {

    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModel.ProfileViewModelFactory(
            application,
            repository
        )
    )
    val cars = viewModel.cars.collectAsState()
    val userNameState = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getCars(onResult = {})
        viewModel.getUserName(onResult = { userNameState.value = true })
    }

    if (userNameState.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 35.dp),
                    text = stringResource(id = R.string.profileScreen),
                    color = Color.Black,
                    fontSize = 18.sp
                )
                IconButton(
                    onClick = {
                        userNameState.value = false
                        viewModel.clearSharedPref()
                        viewModel.deleteTables()
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(top = 35.dp, end = 15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.exit_button),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }

//            Icon(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                contentDescription = "",
//                modifier = Modifier
//                    .clip(RoundedCornerShape(50.dp))
//                    .size(42.dp)
//            )
            }


//        Box(
//            modifier = Modifier.fillMaxWidth(),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                modifier = Modifier.
//                padding(top = 35.dp),
//                text = stringResource(id = R.string.profileScreen),
//                color = Color.Black,
//                fontSize = 18.sp,
//                textAlign = TextAlign.Center
//            )
//            Icon(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                contentDescription = "",
//                modifier = Modifier
//                    .clip(RoundedCornerShape(50.dp))
//                    .size(42.dp)
//            )
//        }
            Spacer(
                modifier = Modifier
                    .padding(top = 50.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(90.dp)
                    .clickable {
                        viewModel.getCars(onResult = { Log.i("CARS: ", cars.value.toString()) })
                    }
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = viewModel.getUserNameFromSharedPref(),
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 40.dp)
            )

            val profileName = stringArrayResource(id = R.array.profile)

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    profileName
                ) { index, item ->
                    if (index != (profileName.size - 1)) {
                        ProfileItem(item, index, navController)
                        Divider(
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
                            thickness = 1.dp,
                            color = DividerGrey
                        )
                    } else {
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .height(50.dp)
                                .clickable {

                                }
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 15.dp, end = 15.dp),
                                text = item,
                                color = Color.Gray,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }


//                FavouriteItem(item)
//                if(index != (profileName.size - 1)) {
//                    Divider(modifier = Modifier
//                        .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
//                        thickness = 1.dp,
//                        color = DividerGrey
//                    )
//                }
                }
            }


//        for(i in settingsName.indices) {
//            if(i != (settingsName.size - 1)) {
//                SettingsItem(settingsName[i])
//                Divider(modifier = Modifier
//                    .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 15.dp),
//                    thickness = 1.dp,
//                    color = DividerGrey
//                )
//            }
//            else {
//                Box(
//                    contentAlignment = Alignment.CenterStart,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 15.dp, end = 15.dp)
//                        .clip(RoundedCornerShape(10.dp))
//                        .height(50.dp)
//                        .clickable {
//
//                        }
//                ) {
//                    Text(
//                        modifier = Modifier
//                            .padding(start = 15.dp, end = 15.dp),
//                        text = settingsName[i],
//                        color = Color.Gray,
//                        fontSize = 15.sp,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
//        }
        }


//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "ProfileScreen",
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            fontSize = 40.sp,
//            textAlign = TextAlign.Center
//        )
//    }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.CircularProgressIndicator(
                color = Blue
            )
        }
    }
}

@Composable
fun ProfileItem(name: String, index: Int, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(50.dp)
            .clickable {
                if (index == 0) {
                    navController.navigate(Screen.CarsScreen.route)
                }
                if (index == 2) {
                    navController.navigate(Screen.BookingScreen.route)
                }
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp),
        ) {
            Text(
                text = name,
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Icon(painter = painterResource(id = R.drawable.buttonarrow), contentDescription = "")
        }
    }
}