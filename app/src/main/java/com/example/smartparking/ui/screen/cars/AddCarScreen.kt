package com.example.smartparking.ui.screen.cars

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartparking.App
import com.example.smartparking.R
import com.example.smartparking.navigation.Screen
import com.example.smartparking.ui.components.TextField
import com.example.smartparking.ui.components.rememberImeState
import com.example.smartparking.ui.screen.login.LoginViewModel
import com.example.smartparking.ui.theme.Blue
import com.example.smartparking.ui.theme.DividerGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCarScreen(navController: NavHostController, context: Context) {
    val textNumber = remember { mutableStateOf("") }
    val textModel = remember { mutableStateOf("") }

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val callback = object : OnBackPressedCallback(
        true // default to enabled
    ) {
        override fun handleOnBackPressed() {
            // Очищаем стек вызовов и переходим на главный экран
            navController.popBackStack(navController.graph.startDestinationId, false)
        }
    }
    onBackPressedDispatcher!!.addCallback(callback)

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) scrollState.animateScrollTo(scrollState.maxValue)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        modifier = Modifier
                            .padding(start = 50.dp),
                        text = "Добавление машины", fontWeight = FontWeight.Medium,
                        color = Color.Black
                        )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Go Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                    containerColor = Color.Transparent
                )
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                hint = "Название машины",
                text = textModel,
                keyboardOptions = KeyboardOptions(),
                keyboardActions = KeyboardActions()
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                hint = "Номер машины",
                text = textNumber,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(),
            )

            Spacer(modifier = Modifier.height(28.dp))

            Box(
                modifier = Modifier.padding(horizontal = 24.dp),
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
                        text = "Добавить машину",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                    )
                }
            }

            Spacer(modifier = Modifier.imePadding())
        }
    }
}

@Preview
@Composable
fun AddCarScreenPreview() {
    AddCarScreen(navController = rememberNavController(), context = LocalContext.current)
}