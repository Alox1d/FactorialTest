package com.sumin.factorialtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    /*
    private val _error = MutableLiveData<Boolean>()
     val error: LiveData<Boolean> = _error

    private val _factorial = MutableLiveData<String>()
     val factorial: LiveData<String> = _factorial

    private val _progress = MutableLiveData<Boolean>()
     val progress: LiveData<Boolean> = _progress
     */

    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }
        val number = value.toLong()

        // calculate
        viewModelScope.launch {
            val result = factorial(number)
            _state.value = Factorial(value = result.toString())
        }
    }

    private suspend fun factorial(number: Long): String {
        return withContext(Dispatchers.Default) {
            var result = BigInteger.ONE
            for (i in 1..number) {
                result = result.multiply(BigInteger.valueOf(i))
            }
            result.toString()
        }
    }

    /*
    private suspend fun factorial(number: Long): String {
        return suspendCoroutine {
            thread {
                var result = BigInteger.ONE
                for (i in 1..number) {
                    result = result.multiply(BigInteger.valueOf(i))
                }
                it.resumeWith(Result.success(result.toString())) // Result.failure - to throw exception
                // return result.toString()
            }
        }
    }

     */
}