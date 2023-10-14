package com.example.domain.utils

import com.example.domain.model.movieActors.CastModel
import com.example.domain.model.movieActors_.Cast


data class ActorState (
    val isLoading: Boolean = false,
    val cast : List<CastModel> = emptyList(),
    val error: String = ""
)