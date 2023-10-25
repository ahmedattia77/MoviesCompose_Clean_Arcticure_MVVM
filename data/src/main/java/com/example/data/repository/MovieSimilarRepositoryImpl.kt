package com.example.data.repository


import com.example.data.mapper.MovieMapper
import com.example.data.remote.ApiService
import com.example.domain.model.movie.MoviesModel
import com.example.domain.repository.MovieSimilarRepository
import javax.inject.Inject

class MovieSimilarRepositoryImpl @Inject constructor(
    private val apiService: ApiService ,
    private val movieMapper: MovieMapper) : MovieSimilarRepository {
    override suspend fun getSimilar(movie_id: Int, page: Int): MoviesModel {
        return movieMapper.fromResponseToModule(apiService.getSimilar(
            movie_id = movie_id ,
            page = page
        ))
    }
}