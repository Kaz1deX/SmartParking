package com.example.smartparking.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatDate(inputDate: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val date = LocalDate.parse(inputDate, inputFormatter)
    return outputFormatter.format(date)
}