package com.example.presentation.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.MoviesUseCase
import com.example.domain.utils.MovieState
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MoviesUseCase
) : ViewModel() {

    var state = mutableStateOf(MovieState())
        private set


    fun getMovie (genre:String){
        useCase.invoke(genre).onEach { result ->
            when (result){
                is Resource.Success -> {
                    state.value = MovieState(
                        movies = result.data
                    )
                }
                is Resource.Error -> {
                    state.value = MovieState(
                        error = result.message ?: "An unexpected value"
                    )
                }
                is Resource.Loading -> {
                    state.value = MovieState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}