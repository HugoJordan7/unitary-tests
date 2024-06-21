package com.example.unitarytests.feature.main.viewModel

import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import com.example.unitarytests.source.NumberRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class MainViewModel(private val numberRepository: NumberRepository): ViewModel() {

    val numberList: StateFlow<MutableList<Int>> get() = _numberList
    private val _numberList = MutableStateFlow<MutableList<Int>>(mutableListOf())

    val errorMessage: StateFlow<String> get() = _errorMessage
    private val _errorMessage = MutableStateFlow("")

    val isFailure: StateFlow<Boolean> get() = _isFailure
    private val _isFailure = MutableStateFlow(false)

    fun append(){
        if (_numberList.value.size < 9){
            val value = numberRepository.getRandomNumber(100)
            val newList = _numberList.value.toMutableList()
            newList.add(value)
            _numberList.value = newList
            _isFailure.value = false
        } else{
            _errorMessage.value = "Limite de 9 números atingidos"
            _isFailure.value = true
        }
    }

    fun remove(){
        if (_numberList.value.isNotEmpty()){
            val newList = _numberList.value.toMutableList()
            newList.removeLast()
            _numberList.value = newList
            _isFailure.value = false
        } else{
            _errorMessage.value = "A lista já está vazia"
            _isFailure.value = true
        }
    }

}