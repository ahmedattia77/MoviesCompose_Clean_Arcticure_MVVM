package com.example.data.remote

import com.example.data.model.movie.ResponseMovies
import com.example.domain.model.movieActors_.ActorsResponse
import com.example.data.model.movieDetails.MovieDetailsResponse
import com.example.data.model.movieGenre.GenreResponse
import com.example.data.model.movieImages.MovieImagesResponse
import com.example.data.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/genre/movie/list?api_key=${Constant.API_KEY}")
    suspend fun getMovieGenre() : GenreResponse

    @GET("3/discover/movie?api_key=${Constant.API_KEY}")
    suspend fun getMovies(
        @Query("with_genres") genresId:Int ,
        @Query("page") page: Int,
    ): ResponseMovies

    @GET("3/movie/{movie_id}?api_key=${Constant.API_KEY}")
    suspend fun getMovieDetails (
        @Path("movie_id") movie_id:Int
    ) : MovieDetailsResponse

    @GET("3/movie/{movie_id}/credits")
    suspend fun getMovieActors(
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key: String = Constant.API_KEY
    ) : ActorsResponse

    @GET("3/movie/{movie_id}/similar")
    suspend fun getSimilar (
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key:String
    ) : ResponseMovies

    @GET("3/movie/{movie_id}/images?api_key=${Constant.API_KEY}")
    suspend fun getMovieImages(
        @Path("movie_id") movie_id:Int,
    ) : MovieImagesResponse


}