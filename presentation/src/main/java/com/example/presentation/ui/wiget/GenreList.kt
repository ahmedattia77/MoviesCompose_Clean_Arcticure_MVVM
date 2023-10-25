package com.example.presentation.ui.wiget


import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.movie.MovieItemModel
import com.example.domain.model.movieGenre.GenreItemModel
import com.example.presentation.R
import com.example.presentation.ui.uiComponent.Chip
import com.example.presentation.ui.uiComponent.ChipGroupCompose
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
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

}