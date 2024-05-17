package com.example.smartparking.ui.screen.registration

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun RegistrationScreen(navController: NavHostController, context: Context) {
    val textUsername = remember { mutableStateOf("") }
    val textLogin = remember { mutableStateOf("") }
    val textEmail = remember { mutableStateOf("") }
    val textPassword1 = remember { mutableStateOf("") }
    val textPassword2 = remember { mutableStateOf("") }

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val viewModel: RegistrationViewModel = viewModel(
        factory = RegistrationViewModel.RegistrationViewModelFactory(
            application,
            repository
        )
    )

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
            modifier = Modifier.padding(top = 10.dp, bottom = 12.dp),
            text = "Регистрация",
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        TextField(
            hint = "Имя пользователя",
            text = textUsername,
            keyboardOptions = KeyboardOptions(),
            keyboardActions = KeyboardActions(),
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            hint = "Логин",
            text = textLogin,
            keyboardOptions = KeyboardOptions(),
            keyboardActions = KeyboardActions(),
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            hint = "Почта",
            text = textEmail,
            keyboardOptions = KeyboardOptions(),
            keyboardActions = KeyboardActions(),
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            hint = "Пароль",
            text = textPassword1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            hint = "Повтор пароля",
            text = textPassword2,
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
                    if (textPassword1.value != textPassword2.value) {
                        Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_SHORT).show()
                    } else {
                        if (textLogin.value != "" && textEmail.value != "" && textUsername.value != "" && textPassword1.value != "") {
                            viewModel.register(
                                textLogin.value,
                                textPassword1.value,
                                textUsername.value,
                                textEmail.value
                            )
                            Toast.makeText(context, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.MapScreen.route)
                        } else {
                            Toast.makeText(context, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                        }
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
                    text = "Зарегистрироваться",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(500))
                )
            }
        }
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(navController = rememberNavController(), context = LocalContext.current)
}