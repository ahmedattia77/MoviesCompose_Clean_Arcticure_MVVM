package com.example.data.repository


import com.example.data.mapper.MovieImagesMapper
import com.example.data.remote.ApiService
import com.example.domain.model.movieImages.MovieImagesModel
import com.example.domain.repository.MovieImagesRepository
import javax.inject.Inject

class MovieImagesRepositoryImpl @Inject constructor(
    private val apiService: ApiService ,
    private val genreMapper: MovieImagesMapper

)  : MovieImagesRepository{
    override suspend fun getMovieImages(movie_id: Int): MovieImagesModel {
        return genreMapper.fromResponseToModule(apiService.getMovieImages
            (movie_id = movie_id))
    }

}