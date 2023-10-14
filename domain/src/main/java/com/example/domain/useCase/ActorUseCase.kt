package com.example.domain.useCase

import com.example.data.model.movieActors.ActorsModel
import com.example.domain.model.movieActors_.ActorsResponse
import com.example.domain.repository.ActorsRepository
import com.example.domain.utils.ActorState
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class ActorUseCase @Inject constructor(private val actorsRepository: ActorsRepository) {

    operator fun invoke(movie_id:Int) :Flow<Resource<ActorsModel>> = flow {
        try {
            emit(Resource.Loading<ActorsModel>())
            val actors = actorsRepository.getActors(movie_id = movie_id)
            emit(Resource.Success<ActorsModel>(actors))
        }catch (e:Exception){
            emit(Resource.Error<ActorsModel>("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}