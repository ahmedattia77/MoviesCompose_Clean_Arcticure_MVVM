package com.example.data.mapper


import com.example.data.model.movieImages.Backdrop
import com.example.data.model.movieImages.MovieImagesResponse
import com.example.domain.model.movieImages.BackdropModel
import com.example.domain.model.movieImages.MovieImagesModel
import javax.inject.Inject

class MovieImagesMapper @Inject constructor() {

    fun fromResponseToModule(obj:MovieImagesResponse) : MovieImagesModel{
        return MovieImagesModel(
            backdrops = obj.backdrops.map { item ->
                fromResponseItemToModule(item)
            }
        )
    }

    private fun fromResponseItemToModule(obj: Backdrop) : BackdropModel{
        return BackdropModel(
            file_path = obj.file_path
        )
    }
}