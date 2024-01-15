package com.sumin.factorialtest

sealed class UiState

object Error : UiState()
object Progress : UiState()
class Result(val factorial: String) : UiState()