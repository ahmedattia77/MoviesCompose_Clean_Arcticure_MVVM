package com.example.domain.repository

import com.example.domain.model.movieImages.MovieImagesModel

interface MovieImagesRepository  {
    suspend fun getMovieImages (movie_id: Int) : MovieImagesModel
}