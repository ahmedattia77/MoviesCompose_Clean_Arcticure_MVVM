package com.example.domain.repository

import com.example.domain.model.movie.MoviesModel

interface MovieSimilarRepository {
    suspend fun getSimilar(movie_id:Int , page:Int) : MoviesModel
}