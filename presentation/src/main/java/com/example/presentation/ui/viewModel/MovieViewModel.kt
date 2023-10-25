package com.example.presentation.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.movie.MovieItemModel
import com.example.domain.model.movieGenre.GenreItemModel
import com.example.domain.useCase.MoviesUseCase
import com.example.domain.utils.GenreState
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

    private var _movieState = mutableStateOf(MovieState())
    var genre = mutableStateOf(28)

    val movies: State<MovieState>
        get() = _movieState


    init {
        getMovie()
    }

    fun getMovie (){
        useCase.invoke(genre.value).onEach { result ->
            when (result){
                is Resource.Success -> {
                    _movieState.value = MovieState(
                        movies = result.data
                    )
                }
                is Resource.Error -> {
                    _movieState.value = MovieState(
                        error = result.message ?: "An unexpected value"
                    )
                }
                is Resource.Loading -> {
                    _movieState.value = MovieState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}