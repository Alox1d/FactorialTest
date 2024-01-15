package com.sumin.factorialtest

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> = _state

    fun calculate(value: String?) {
        _state.value = UiState(
            isInProgress = true,
        )
        if (value.isNullOrBlank()) {
            _state.value = UiState(
                isError = true
            )
            return
        }
        viewModelScope.launch {
            val number = value.toLong()
            // calculate
            delay(1000)
            _state.value = UiState(
                factorial = number.toString()
            )
        }
    }
}