package com.example.presentation.ui.wiget


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LOGGER
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.presentation.navigation.Screen
import com.example.presentation.ui.uiComponent.MovieItem
import com.example.presentation.ui.uiComponent.CircleProgressbar
import com.example.presentation.ui.viewModel.MovieDetailsViewModel
import com.example.presentation.ui.viewModel.MovieViewModel
import com.example.presentation.ui.viewModel.SharedViewModel
import com.example.presentation.utils.Constant

@SuppressLint("UnrememberedMutableState")
@Composable
fun MovieList(
    navHostController: NavHostController,
    viewModel: MovieViewModel = hiltViewModel(),
    detailsViewModel: MovieDetailsViewModel = hiltViewModel() ,
    sharedViewModel: SharedViewModel) {
    var context  = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        val movies = viewModel.movies.value.movies!!.flow.collectAsLazyPagingItems()

        if (viewModel.movies.value.isLoading) {
            CircleProgressbar()
        } else if (viewModel.movies.value.error.isNotEmpty()) {

        } else {

            LazyVerticalGrid (
                contentPadding = PaddingValues(top = 12.dp),
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                    },
                content = {
                    items(movies.itemCount) { index ->
                        Card(
                            backgroundColor = Color.White,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize(),
                            elevation = 15.dp,
                        ) {
                            MovieItem(movie = movies[index]!! ,
                                onSelected = { movie ->
                                    Log.i("sendIdIssue" ,  "clicked movie id : ${movie.id}")
                                    sharedViewModel.sendId(movie.id)
                                    navHostController.navigate(Screen.Details.route)
                                })
                        }
                    }
                }
            )
        }
    }
}
