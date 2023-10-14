package com.example.domain.model.movieActors_

data class ActorsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)