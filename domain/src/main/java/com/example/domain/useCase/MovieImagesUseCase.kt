package com.example.domain.useCase


import com.example.domain.model.movieImages.MovieImagesModel
import com.example.domain.repository.MovieImagesRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieImagesUseCase @Inject constructor(private val repository: MovieImagesRepository) {

    operator fun invoke(movie_id:Int): Flow<Resource<MovieImagesModel>> = flow {
        try {
            emit(Resource.Loading<MovieImagesModel>())
            val images = repository.getMovieImages(movie_id = movie_id )
            emit(Resource.Success<MovieImagesModel>(images))
        }catch (e:Exception){
            emit(Resource.Error<MovieImagesModel>("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}