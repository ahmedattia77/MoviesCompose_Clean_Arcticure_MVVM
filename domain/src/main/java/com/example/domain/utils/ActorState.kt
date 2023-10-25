package com.example.domain.utils

import com.example.domain.model.movieActors.CastModel


data class ActorState (
    val isLoading: Boolean = false,
    val cast : List<CastModel> = emptyList(),
    val error: String = ""
)