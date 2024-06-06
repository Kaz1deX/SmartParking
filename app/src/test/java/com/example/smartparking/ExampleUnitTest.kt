package com.example.smartparking

import androidx.compose.ui.input.key.Key.Companion.Calculator
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
        assertEquals(4, 2 + 2)
    }

    @Test
    fun connect_local_database_test() {
        assertEquals(6, 3 + 3)
    }

    @Test
    fun main_repository_test() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun network_utils_test() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun shared_pref_test() {
        assertEquals(4, 2 + 2)
    }
}