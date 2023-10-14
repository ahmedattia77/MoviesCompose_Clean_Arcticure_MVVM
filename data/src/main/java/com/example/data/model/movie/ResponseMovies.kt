package com.example.data.model.movie

data class ResponseMovies(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)