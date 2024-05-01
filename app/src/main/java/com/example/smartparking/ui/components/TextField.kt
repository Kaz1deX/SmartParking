package com.example.smartparking.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import com.example.smartparking.ui.theme.Blue

@Composable
fun TextField(
    hint: String,
    text: MutableState<String>,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    readOnly: Boolean = false,
    isPassword: Boolean = false,
    horizontalPadding: Dp = 24.dp,
    singleLine: Boolean = true
) {
    val passwordVisible = remember { mutableStateOf(false) }
    val visualTransformation = if (isPassword && !passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None

    val trailingIcon = if (!isPassword) null else {
        if (passwordVisible.value) {
            Icons.Filled.Visibility
        } else {
            Icons.Filled.VisibilityOff
        }
    }

    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(text = hint, color = Gray) },

        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        readOnly = readOnly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            focusedBorderColor = Blue,
            unfocusedBorderColor = Blue
        ),
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon?.let { icon ->
            {
                IconButton(onClick = {
                    passwordVisible.value = !passwordVisible.value
                }) {
                    Icon(icon, contentDescription = null, tint = Gray)
                }
            }
        }
    )
}