package com.example.domain.repository

import com.example.domain.model.movieDetails.MovieDetailsModel

interface MovieDetailsRepository {
    suspend fun getDetails (movie_id:Int) : MovieDetailsModel
}