package com.example.testapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel :ViewModel() {
    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    fun changeText(newText: String) {
        _text.value = newText
    }
}