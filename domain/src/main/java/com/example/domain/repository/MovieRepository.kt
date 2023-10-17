package com.example.domain.repository

import com.example.domain.model.movie.MoviesModel


interface MovieRepository {
    suspend fun getMovies (withGenres:Int , page:Int) : MoviesModel
}