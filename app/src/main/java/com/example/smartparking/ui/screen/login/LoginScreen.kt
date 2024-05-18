package com.example.smartparking.ui.screen.login

import android.app.Activity
import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.smartparking.ui.screen.registration.RegistrationViewModel
import com.example.smartparking.ui.theme.Blue

@Composable
fun LoginScreen(navController: NavHostController, context: Context) {
    val textLogin = remember { mutableStateOf("") }
    val textPassword = remember { mutableStateOf("") }

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.LoginViewModelFactory(
            application,
            repository
        )
    )

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
            text = "Smart Parking",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(240.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.padding(top = 56.dp, bottom = 12.dp),
            text = "Вход",
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        TextField(
            hint = "Логин",
            text = textLogin,
            keyboardOptions = KeyboardOptions(),
            keyboardActions = KeyboardActions(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            hint = "Пароль",
            text = textPassword,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(28.dp))

        Box(
            modifier = Modifier.padding(horizontal = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = {
                    if (textLogin.value != "" && textPassword.value != "") {
                        viewModel.auth(
                            textLogin.value,
                            textPassword.value,
                            onResult = {
                                when (it) {
                                    "User not found" -> {
                                        Toast.makeText(context, "Такого пользователя не существует!", Toast.LENGTH_SHORT).show()
                                    }
                                    "Invalid password" -> {
                                        Toast.makeText(context, "Неверный пароль!", Toast.LENGTH_SHORT).show()
                                    }
                                    null -> {
                                        Toast.makeText(context, "Ошибка на сервере!", Toast.LENGTH_SHORT).show()
                                    }
                                    "" -> {
                                        Toast.makeText(context, "Ошибка на сервере!", Toast.LENGTH_SHORT).show()
                                    }
                                    else -> {
                                        navController.navigate(Screen.MapScreen.route)
                                    }
                                }
                            }
                        )


                    } else {
                        Toast.makeText(context, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                    }
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
                    text = "Войти",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.padding(24.dp))

        Divider(
            modifier = Modifier.padding(horizontal = 36.dp),
            thickness = 1.dp,
            color = Gray
        )

        Row(
            modifier = Modifier.padding(bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Нет аккаунта?", color = Gray, fontSize = 16.sp)
            TextButton(onClick = {
                navController.navigate(Screen.RegistrationScreen.route)
            }) {
                Text(
                    text = "Регистрация",
                    color = Blue,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.imePadding())
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), context = LocalContext.current)
}