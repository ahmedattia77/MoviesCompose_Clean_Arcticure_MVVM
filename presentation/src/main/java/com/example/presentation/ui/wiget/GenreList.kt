package com.example.presentation.ui.wiget


import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.movieGenre.GenreItemModel
import com.example.presentation.R
import com.example.presentation.ui.uiComponent.Chip
import com.example.presentation.ui.uiComponent.CircleProgressbar
import com.example.presentation.ui.viewModel.GenreViewModel
import com.example.presentation.ui.viewModel.MovieViewModel
import com.example.presentation.ui.viewModel.SharedViewModel
import java.util.*


@SuppressLint("UnrememberedMutableState")
@Composable
fun GenreList(
    genreViewModel: GenreViewModel = hiltViewModel(),
    moviesViewModel: MovieViewModel = hiltViewModel(),
    navHostController: NavHostController ,
    sharedViewModel: SharedViewModel
) {


    if (genreViewModel.genres.value.isLoading) {
        CircleProgressbar()
    }
    else if (genreViewModel.genres.value.genre.isNotEmpty()) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(PaddingValues(8.dp)),
            text = "Movie Category",
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold ,
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 16.sp
        )
        LazyRow {
            items(genreViewModel.genres.value.genre) { genre ->
                Chip(
                    genre = genre,
                    selected = genreViewModel._selectedGenre.value == genre,
                    onSelected = {
                        if (genreViewModel._selectedGenre.value == genre)
                            genreViewModel.setSelectedGenre(GenreItemModel(id = -1 ,name = ""))
                        else{
                            genreViewModel.setSelectedGenre(genre)
                        }
                    },
                    modifier = Modifier
                )
            }
        }

        if (genreViewModel._selectedGenre.value.name.isNotEmpty()) {
            Log.i("selectGenre : " , genreViewModel._selectedGenre.value.id.toString())
            moviesViewModel.getMovie(genreViewModel._selectedGenre.value.id)
            MovieList(navHostController = navHostController ,sharedViewModel =sharedViewModel)
        }

    }
    else if (genreViewModel.genres.value.error.isNotEmpty()) {
        Toast.makeText(LocalContext.current,
            moviesViewModel.movies.value.error,
            Toast.LENGTH_SHORT).show()
    }

}