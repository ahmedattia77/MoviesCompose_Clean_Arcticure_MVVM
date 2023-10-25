package com.example.movies20

import com.example.data.mapper.ActorMapper
import com.example.data.mapper.GenreMapper
import com.example.data.mapper.MovieDetailsMapper
import com.example.data.mapper.MovieImagesMapper
import com.example.data.mapper.MovieMapper
import com.example.data.remote.ApiService
import com.example.data.repository.ActorsRepositoryImp
import com.example.data.repository.GenreRepositoryImpl
import com.example.data.repository.MovieDetailsRepositoryImpl
import com.example.data.repository.MovieImagesRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.data.repository.MovieSimilarRepositoryImpl
import com.example.data.utils.Constant
import com.example.domain.repository.ActorsRepository
import com.example.domain.repository.GenreRepository
import com.example.domain.repository.MovieDetailsRepository
import com.example.domain.repository.MovieImagesRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieSimilarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi () : Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService =
                retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideGenreRepo (
        apiService: ApiService ,
        genreMapper: GenreMapper
    ) : GenreRepository = GenreRepositoryImpl (apiService , genreMapper)

    @Provides
    @Singleton
    fun provideMovieRepo (
        apiService: ApiService ,
        movieMapper: MovieMapper
    ) : MovieRepository = MovieRepositoryImpl (apiService , movieMapper)

    @Provides
    @Singleton
    fun provideMovieDetailsRepo (
        apiService: ApiService ,
        movieDetailsMapper: MovieDetailsMapper
    ) : MovieDetailsRepository = MovieDetailsRepositoryImpl (apiService , movieDetailsMapper)

    @Provides
    @Singleton
    fun provideActorRepo (
        apiService: ApiService ,
        actorMapper: ActorMapper
    ) : ActorsRepository = ActorsRepositoryImp (apiService , actorMapper )

    @Provides
    @Singleton
    fun provideImagesRepo (
        apiService: ApiService ,
        imagesMapper: MovieImagesMapper
    ) : MovieImagesRepository = MovieImagesRepositoryImpl (apiService , imagesMapper)

    @Provides
    @Singleton
    fun provideMovieSimilarRepo(
        apiService: ApiService,
        movieMapper: MovieMapper
    ) : MovieSimilarRepository = MovieSimilarRepositoryImpl (apiService , movieMapper)

}