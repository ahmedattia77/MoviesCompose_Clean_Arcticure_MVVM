package com.example.domain.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.domain.model.movie.MovieItemModel
import com.example.domain.pagingSource.MoviesSimilarPagingSource
import com.example.domain.repository.MovieSimilarRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MoviesSimilarUseCase @Inject constructor(private val repository: MovieSimilarRepository) {

    operator fun invoke(movie_id:Int): Flow<Resource<Pager<Int,MovieItemModel>>> = flow {
        try {
            emit(Resource.Loading<Pager<Int,MovieItemModel>>())
            val getMovies = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = {
                    MoviesSimilarPagingSource(repository,movie_id)
                }
            )
            emit(Resource.Success<Pager<Int, MovieItemModel>>(getMovies))
        }catch (e:Exception){
            emit(Resource.Error<Pager<Int,MovieItemModel>>("${e.localizedMessage} : An unexpected error happened"))
        }catch (e:IOException){
            emit(Resource.Error<Pager<Int , MovieItemModel>>("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}