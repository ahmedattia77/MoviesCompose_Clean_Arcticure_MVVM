package com.example.data.model.movieImages

data class MovieImagesResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)