package com.example.domain.useCase

import com.example.domain.model.movieDetails.MovieDetailsModel
import com.example.domain.model.movieGenre.GenreModel
import com.example.domain.repository.GenreRepository
import com.example.domain.repository.MovieDetailsRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository) {

    operator fun invoke(movie_id:Int): Flow<Resource<MovieDetailsModel>> = flow {
        try {
            emit(Resource.Loading<MovieDetailsModel>())
            val cats = repository.getDetails(movie_id = movie_id )
            emit(Resource.Success<MovieDetailsModel>(cats))
        }catch (e:Exception){
            emit(Resource.Error<MovieDetailsModel>
                ("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}