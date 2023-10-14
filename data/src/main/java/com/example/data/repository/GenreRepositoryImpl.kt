package com.example.data.repository

import android.util.Log
import com.example.data.mapper.GenreMapper
import com.example.data.remote.ApiService
import com.example.domain.model.movieGenre.GenreModel
import com.example.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val apiService: ApiService ,
    private val genreMapper: GenreMapper

)  : GenreRepository{
    override suspend fun getGenre(): GenreModel {
        Log.i("genreResponse" , "before mapping : " +apiService.getMovieGenre().genres[0].name)
        Log.i("genreResponse" , "after mapping : " +
        genreMapper.fromResponseToModule(apiService.getMovieGenre()).genres[0].name)
        return genreMapper.fromResponseToModule(apiService.getMovieGenre())
    }
}