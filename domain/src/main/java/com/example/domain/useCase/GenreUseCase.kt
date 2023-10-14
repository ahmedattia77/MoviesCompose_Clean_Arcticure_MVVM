package com.example.domain.useCase

import com.example.domain.model.movieGenre.GenreModel
import com.example.domain.repository.GenreRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreUseCase @Inject constructor(private val repository: GenreRepository) {

    operator fun invoke(): Flow<Resource<GenreModel>> = flow {
        try {
            emit(Resource.Loading<GenreModel>())
            val cats = repository.getGenre()
            emit(Resource.Success<GenreModel>(cats))
        }catch (e:Exception){
            emit(Resource.Error<GenreModel>("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}