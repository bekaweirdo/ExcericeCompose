package com.example.excericecompose.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.excericecompose.model.User
import com.example.excericecompose.model.users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConversionVm : ViewModel() {

    val expandedCardIdsList = MutableStateFlow(listOf<Int>())
    val cards = MutableStateFlow(listOf<User>())

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                cards.emit(users)
            }
        }
    }

    fun onCardArrowClicked(cardId: Int){
        expandedCardIdsList.value = expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }

    fun fahrenheitConverter(fahrenheit: Int): String {
        val celcius = (((fahrenheit - 32) * 5) / 9).toDouble()
        return "$fahrenheit °F = $celcius °C"
    }
}