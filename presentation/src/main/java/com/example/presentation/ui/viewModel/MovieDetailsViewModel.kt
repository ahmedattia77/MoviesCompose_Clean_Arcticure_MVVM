package com.example.presentation.ui.viewModel

import android.util.Log
import androidx.appcompat.resources.Compatibility.Api15Impl
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.ActorUseCase
import com.example.domain.useCase.MovieDetailsUseCase
import com.example.domain.useCase.MovieImagesUseCase
import com.example.domain.useCase.MoviesSimilarUseCase
import com.example.domain.utils.ActorState
import com.example.domain.utils.MovieDetailsState
import com.example.domain.utils.MovieImagesState
import com.example.domain.utils.MovieSimilarState
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val useCase: MovieDetailsUseCase,
    private val actorUseCase: ActorUseCase,
    private val movieImagesUseCase: MovieImagesUseCase,
    private val moviesSimilarUseCase: MoviesSimilarUseCase
) : ViewModel() {

    //980489
    private var movie_id:MutableState<Int> = mutableStateOf(0)
    var movieState = mutableStateOf(MovieDetailsState())
        private set

    var actorState = mutableStateOf(ActorState())
        private set

    var movieImages = mutableStateOf(MovieImagesState())
        private set
    var movieSimilar = mutableStateOf(MovieSimilarState())
        private set

    init {
        getMovieSimilar()
        getMovieImages()
        getActor()
        getMovieDetails()
    }

    fun setId (movieId:Int){
        this.movie_id.value = movieId
    }

    fun updateState (){
        getMovieSimilar()
        getMovieImages()
        getActor()
        getMovieDetails()
    }

     fun getMovieDetails (){
        useCase.invoke(movie_id.value).onEach { result ->
            when (result){
                is Resource.Success -> {
                    movieState.value = MovieDetailsState(
                        movieDetails = result.data
                    )
                }
                is Resource.Error -> {
                    movieState.value = MovieDetailsState(
                        error = result.message ?: "An unexpected value"
                    )
                }
                is Resource.Loading -> {
                    movieState.value = MovieDetailsState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


     fun getActor (){
        actorUseCase.invoke(movie_id.value).onEach { result ->
            when (result){
                is Resource.Success -> {
                    Log.i("viewModel" ,"success ${result.message.toString()}")
                    actorState.value = ActorState(
                        cast = result.data?.cast !!
                    )
                }
                is Resource.Error -> {
                    actorState.value = ActorState(
                        error = result.message ?: "An unexpected value"
                    )
                    Log.i("viewModel" ,"error ${result.message.toString()}")
                }
                is Resource.Loading -> {
                    actorState.value = ActorState(
                        isLoading = true
                    )
                    Log.i("viewModel" ,"loading")
                }
            }
        }.launchIn(viewModelScope)
    }


     fun getMovieImages (){
        movieImagesUseCase.invoke(movie_id.value).onEach { result ->
            when (result){
                is Resource.Success -> {
                    movieImages.value = MovieImagesState(
                        backdropList = result.data?.backdrops!!
                    )
                }
                is Resource.Error -> {
                    movieImages.value = MovieImagesState(
                        error = result.message ?: "An unexpected value"
                    )
                }
                is Resource.Loading -> {
                    movieImages.value = MovieImagesState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieSimilar (){
        moviesSimilarUseCase.invoke(movie_id.value).onEach { result ->
            when(result){
                is Resource.Success -> {
                    movieSimilar.value = MovieSimilarState(
                        movies = result.data
                    )
                }
                is Resource.Loading -> {
                    movieSimilar.value = MovieSimilarState(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    movieSimilar.value = MovieSimilarState(
                        error = result.message ?: "An unexpected value"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}