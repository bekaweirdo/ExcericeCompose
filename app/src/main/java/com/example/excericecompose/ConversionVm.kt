package com.example.excericecompose

import androidx.lifecycle.ViewModel

class ConversionVm : ViewModel() {

    fun fahrenheitConverter(fahrenheit: Int): String {
        val celcius = (((fahrenheit - 32) * 5) / 9).toDouble()
        return "$fahrenheit °F = $celcius °C"
    }
}