package com.example.data.mapper

import com.example.data.model.movieActors.ActorsModel
import com.example.domain.model.movieActors.CastModel
import com.example.domain.model.movieActors_.ActorsResponse
import com.example.domain.model.movieActors_.Cast
import com.example.domain.model.movieActors_.Crew

import javax.inject.Inject

class ActorMapper @Inject constructor() {

    fun fromRemoteActorToModule(obj: ActorsResponse) : ActorsModel {
        return ActorsModel(
            cast = obj.cast.map {cast ->
                fromItemActorToModule(cast)
            }
        )
    }

    private fun fromItemActorToModule(obj: Cast) : CastModel{
        return CastModel(
            profilePath = obj.profile_path ,
            name = obj.name ,
            id = obj.id
        )
    }

}