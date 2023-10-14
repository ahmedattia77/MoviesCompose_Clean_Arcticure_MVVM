package com.example.data.repository

import android.util.Log
import com.example.data.mapper.ActorMapper
import com.example.data.model.movieActors.ActorsModel
import com.example.data.remote.ApiService
import com.example.domain.model.movieActors_.ActorsResponse
import com.example.domain.repository.ActorsRepository
import javax.inject.Inject

class ActorsRepositoryImp @Inject constructor(
    val apiService: ApiService ,
    val actorMapper: ActorMapper
) : ActorsRepository {

    override suspend fun getActors(movie_id: Int): ActorsModel {
        Log.i("actorResponse",
            "before mapping : "
                    + apiService.getMovieActors(movie_id = movie_id).cast[0].name )

        Log.i("actorResponse",
            "after mapping : "
                    + actorMapper.
            fromRemoteActorToModule(apiService.getMovieActors(movie_id = movie_id)).cast[0].name)

        return actorMapper.fromRemoteActorToModule(apiService.getMovieActors(movie_id = movie_id))
//        return apiService.getMovieActors(movie_id = movie_id)

    }

}