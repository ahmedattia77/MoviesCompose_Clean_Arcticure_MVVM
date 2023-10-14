package com.example.domain.model.movieDetails

data class MovieDetailsModel(
    val adult: Boolean,
    val genres: List<GenreModel>,
    val id: Int,
    val original_language: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)