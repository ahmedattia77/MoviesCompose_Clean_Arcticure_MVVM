package com.example.presentation.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.movieGenre.GenreItemModel
import com.example.domain.useCase.GenreUseCase
import com.example.domain.utils.GenreState
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val useCase: GenreUseCase
) : ViewModel() {

    var _state = mutableStateOf(GenreState())
        private set
    var _selectedGenre = mutableStateOf(GenreItemModel(id = 28 , name = "Action"))
        private set

    val genres: State<GenreState>
        get() = _state

    fun setSelectedGenre(selectedGenre: GenreItemModel){
        _selectedGenre.value = selectedGenre
        _selectedGenre.value = _selectedGenre.value
    }

    init {
        getGenre()
    }

    private fun getGenre (){
        useCase.invoke().onEach { result ->
            when (result){
                is Resource.Success -> {
                    _state.value = GenreState(
                        genre = result.data?.genres ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = GenreState(
                        error = result.message ?: "An unexpected value"
                    )
                }
                is Resource.Loading -> {
                    _state.value = GenreState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}