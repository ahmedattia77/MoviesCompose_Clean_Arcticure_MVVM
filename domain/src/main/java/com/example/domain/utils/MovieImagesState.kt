package com.example.domain.utils

import com.example.domain.model.movieImages.BackdropModel


data class MovieImagesState (
    val isLoading: Boolean = false,
    val backdropList : List<BackdropModel> = emptyList(),
    val error: String = ""
)