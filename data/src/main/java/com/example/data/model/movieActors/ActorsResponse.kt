package com.example.data.model.movieActors

import com.example.domain.model.movieActors_.Cast
import com.example.domain.model.movieActors_.Crew

data class ActorsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)