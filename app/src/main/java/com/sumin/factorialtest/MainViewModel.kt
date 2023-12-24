package com.sumin.factorialtest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val coroutineScope =
        CoroutineScope(Dispatchers.Main + CoroutineName("My Coroutine Name"))

    /*
    private val _error = MutableLiveData<Boolean>()
     val error: LiveData<Boolean> = _error

    private val _factorial = MutableLiveData<String>()
     val factorial: LiveData<String> = _factorial

    private val _progress = MutableLiveData<Boolean>()
     val progress: LiveData<Boolean> = _progress
     */

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }

        // calculate
        coroutineScope.launch {
            val number = value.toLong()
            val result = withContext(Dispatchers.Default) {
                factorial(number)
            }
            _state.value = (Factorial(value = result))
            Log.d("VM", "calculate: $coroutineContext")
        }
    }

    private fun factorial(number: Long): String {
        var result = BigInteger.ONE
        for (i in 1..number) {
            result = result.multiply(BigInteger.valueOf(i))
        }
        return result.toString()
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