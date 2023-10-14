package com.example.domain.utils

import com.example.domain.model.movieDetails.MovieDetailsModel

data class MovieDetailsState (
    val isLoading: Boolean = false,
    val movieDetails : MovieDetailsModel ?  = null,
    val error: String = ""
)