package com.sumin.factorialtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> = _state

    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }
        viewModelScope.launch {
            val number = value.toLong()
            val result = factorial(number)
            _state.value = Result(factorial = result)
        }
    }

    private suspend fun factorial(number: Long): String = withContext(Dispatchers.Default) {
        var result = BigInteger.ONE
        for (i in 1..number) {
            result = result.multiply(BigInteger.valueOf(i))
        }
        return@withContext result.toString()
    }
}