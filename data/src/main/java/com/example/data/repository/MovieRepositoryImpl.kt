package com.example.data.repository

import android.util.Log
import com.example.data.mapper.MovieMapper
import com.example.data.remote.ApiService
import com.example.domain.model.movie.MoviesModel
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService ,
    private val movieMapper: MovieMapper) : MovieRepository {
    override suspend fun getMovies(withGenres: Int, page: Int): MoviesModel {

        return movieMapper.fromResponseToModule(apiService.getMovies(
            genresId = withGenres ,
            page = page
        ))
    }
}