package com.example.domain.utils

import com.example.domain.model.movieGenre.GenreItemModel

data class GenreState (
    val isLoading: Boolean = false,
    val genre : List<GenreItemModel>  = emptyList(),
    val error: String = ""
)