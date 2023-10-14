package com.example.domain.repository

import com.example.domain.model.movieGenre.GenreModel

interface GenreRepository {
    suspend fun getGenre () : GenreModel
}