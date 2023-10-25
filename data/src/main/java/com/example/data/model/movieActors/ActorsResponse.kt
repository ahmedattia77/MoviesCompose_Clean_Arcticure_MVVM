package com.example.data.model.movieActors

data class ActorsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)