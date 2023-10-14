package com.example.presentation.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.ui.uiComponent.ChipGroupCompose
import com.example.presentation.ui.uiComponent.CircleProgressbar
import com.example.presentation.ui.viewModel.GenreViewModel
import com.example.presentation.ui.viewModel.MovieDetailsViewModel

@Composable
fun HomeScreen(
    genreViewModel: GenreViewModel = hiltViewModel(),
    detailsViewModel: MovieDetailsViewModel = hiltViewModel(),
){
    val context = LocalContext.current

    if (genreViewModel.state.value.isLoading)
        CircleProgressbar()

    else if (genreViewModel.state.value.genre.isNotEmpty()){
        Column (
            verticalArrangement = Arrangement.Top ,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
//            Spacer(modifier = Modifier.height(16.dp))
//            ChipGroupCompose(genreViewModel.state.value.genre)
//            Spacer(modifier = Modifier.height(24.dp))
            MovieDetails()
        }
    }

}