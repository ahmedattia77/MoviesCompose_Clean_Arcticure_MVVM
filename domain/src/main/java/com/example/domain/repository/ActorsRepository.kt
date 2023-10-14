package com.example.domain.repository

import com.example.data.model.movieActors.ActorsModel
import com.example.domain.model.movieActors_.ActorsResponse

interface ActorsRepository {
    suspend fun getActors (movie_id :Int) : ActorsModel
}