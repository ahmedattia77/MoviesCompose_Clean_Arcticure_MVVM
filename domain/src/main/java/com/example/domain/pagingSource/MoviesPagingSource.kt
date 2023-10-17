package com.example.domain.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.movie.MovieItemModel
import com.example.domain.repository.MovieRepository

class MoviesPagingSource(
    private val movieRepository: MovieRepository,
    private val genreId:Int
    ) : PagingSource<Int , MovieItemModel>(){

    override fun getRefreshKey(state: PagingState<Int, MovieItemModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemModel> {
        return try {
            val page = params.key ?: 1
            val response = movieRepository.getMovies(page = page , withGenres = genreId)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}