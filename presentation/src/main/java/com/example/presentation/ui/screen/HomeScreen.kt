package com.example.presentation.ui.screen


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
import androidx.navigation.NavHostController
import com.example.presentation.navigation.Screen
import com.example.presentation.ui.uiComponent.ChipGroupCompose
import com.example.presentation.ui.uiComponent.CircleProgressbar
import com.example.presentation.ui.viewModel.GenreViewModel
import com.example.presentation.ui.viewModel.MovieDetailsViewModel
import com.example.presentation.ui.viewModel.SharedViewModel
import com.example.presentation.ui.wiget.GenreList

@Composable
fun HomeScreen(
    genreViewModel: GenreViewModel = hiltViewModel(),
    detailsViewModel: MovieDetailsViewModel = hiltViewModel(),
    navHostController: NavHostController ,
    sharedViewModel: SharedViewModel
){
    val context = LocalContext.current

    if (genreViewModel._state.value.isLoading)
        CircleProgressbar()

    else if (genreViewModel._state.value.genre.isNotEmpty()){
        Column (
            verticalArrangement = Arrangement.Top ,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
//            Spacer(modifier = Modifier.height(16.dp))
//            ChipGroupCompose(genreViewModel._state.value.genre)
//            Spacer(modifier = Modifier.height(24.dp))
            GenreList(navHostController = navHostController , sharedViewModel = sharedViewModel)
        }
    }

}