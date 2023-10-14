package com.example.data.repository

import com.example.data.mapper.MovieDetailsMapper
import com.example.data.remote.ApiService
import com.example.domain.model.movieDetails.MovieDetailsModel
import com.example.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService ,
    private val movieDetailsMapper: MovieDetailsMapper

)  : MovieDetailsRepository{
    override suspend fun getDetails(movie_id: Int): MovieDetailsModel {
        return movieDetailsMapper
            .fromResponseToModule(apiService.getMovieDetails(movie_id = movie_id))
    }
}