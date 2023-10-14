package com.example.data.mapper

import com.example.data.model.movie.MovieResult
import com.example.data.model.movie.ResponseMovies
import com.example.domain.model.movie.MovieItemModel
import com.example.domain.model.movie.MoviesModel
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun fromResponseToModule(obj: ResponseMovies) : MoviesModel {
        return MoviesModel(
            page = obj.page ,
            total_pages = obj.total_pages ,
            total_results = obj.total_results ,
            results = obj.results.map { movie ->
                fromResponseItemToModule(movie)
            })
    }

    private fun fromResponseItemToModule(obj: MovieResult) : MovieItemModel {
        return MovieItemModel(
            id = obj.id ,
            original_language = obj.original_language ,
            poster_path = obj.poster_path,
            release_date = obj.release_date,
            title = obj.title
        )
    }
}