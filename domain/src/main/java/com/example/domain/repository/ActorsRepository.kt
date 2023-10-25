package com.example.domain.repository

import com.example.data.model.movieActors.ActorsModel

interface ActorsRepository {
    suspend fun getActors (movie_id :Int) : ActorsModel
}