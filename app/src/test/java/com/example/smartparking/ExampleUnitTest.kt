package com.example.smartparking

import androidx.compose.ui.input.key.Key.Companion.Calculator
import com.example.smartparking.util.formatDate
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun connect_to_server_test() {
        val dateNotFormat = "2024-08-06"
        val result = formatDate(dateNotFormat)
        assertEquals("06.08.2024", result)
    }
}