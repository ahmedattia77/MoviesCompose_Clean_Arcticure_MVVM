package com.example.domain.utils

import androidx.paging.Pager
import com.example.domain.model.movie.MovieItemModel


data class MovieState (
    val isLoading: Boolean = false,
    val movies : Pager<Int , MovieItemModel> ? = null,
    val error: String = ""
)