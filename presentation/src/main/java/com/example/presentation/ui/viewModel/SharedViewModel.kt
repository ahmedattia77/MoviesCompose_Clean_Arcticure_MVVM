package com.example.presentation.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _movie_id : MutableState<Int> = mutableStateOf(0)

    val movie_id = _movie_id


    fun sendId (movie_id:Int){
        _movie_id.value = movie_id
    }

}