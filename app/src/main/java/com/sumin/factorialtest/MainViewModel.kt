package com.sumin.factorialtest

import android.text.Editable.Factory
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumin.factorialtest.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            // calculate
            delay(1000)
            _state.value = Result(factorial = number.toString())
        }
    }
}