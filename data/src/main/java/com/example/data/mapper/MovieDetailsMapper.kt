package com.example.data.mapper

import com.example.data.model.movieDetails.Genre
import com.example.data.model.movieDetails.MovieDetailsResponse
import com.example.domain.model.movieDetails.GenreModel
import com.example.domain.model.movieDetails.MovieDetailsModel
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {
    fun fromResponseToModule(obj:MovieDetailsResponse) : MovieDetailsModel{
        return MovieDetailsModel(
            adult = obj.adult ,
            id = obj.id,
            original_language = obj.original_language ,
            overview = obj.overview,
            poster_path = obj.poster_path ,
            release_date = obj.release_date ,
            title = obj.title,
            vote_average = obj.vote_average ,
            genres = obj.genres.map { item ->
                fromGenreResponseToModel(item)
            }
        )
    }
}

    private fun fromGenreResponseToModel(genre:Genre) : GenreModel{
        return GenreModel(
            name = genre.name
        )
    }