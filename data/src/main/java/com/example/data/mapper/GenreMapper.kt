package com.example.data.mapper

import com.example.data.model.movieGenre.Genre
import com.example.data.model.movieGenre.GenreResponse
import com.example.domain.model.movieGenre.GenreItemModel
import com.example.domain.model.movieGenre.GenreModel
import javax.inject.Inject

class GenreMapper @Inject constructor() {

    fun fromResponseToModule(obj:GenreResponse) : GenreModel{
        return GenreModel(
            genres = obj.genres.map { genre ->
            fromResponseItemToModule(genre)
        })
    }

    private fun fromResponseItemToModule(obj: Genre) : GenreItemModel{
        return GenreItemModel(
            name = obj.name,
            id = obj.id,
        )
    }
}