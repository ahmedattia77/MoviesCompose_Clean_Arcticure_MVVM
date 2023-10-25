package com.example.presentation.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.navigation.Screen
import com.example.presentation.ui.uiComponent.ChipGroupCompose
import com.example.presentation.ui.uiComponent.CircleProgressbar
import com.example.presentation.ui.uiComponent.MovieItem
import com.example.presentation.ui.viewModel.GenreViewModel
import com.example.presentation.ui.viewModel.MovieViewModel
import com.example.presentation.ui.viewModel.SharedViewModel


@Composable
fun HomeScreen(
    genreViewModel: GenreViewModel = hiltViewModel(),
    moviesViewModel: MovieViewModel = hiltViewModel(),
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        if (genreViewModel._state.value.isLoading)
            CircleProgressbar()
        else if (genreViewModel._state.value.genre.isNotEmpty()) {

            if (genreViewModel.genres.value.isLoading) {
                CircleProgressbar()
            } else if (genreViewModel.genres.value.genre.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(8.dp)),
                    text = "Movie Category",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontSize = 18.sp)

                ChipGroupCompose(chipList = genreViewModel.genres.value.genre ,
                    onClick = { item ->
                        moviesViewModel.genre.value = item.id
                        moviesViewModel.getMovie()
                    })

                Spacer(modifier = Modifier.height(12.dp))
            if (moviesViewModel.movies.value.isLoading)
                CircleProgressbar()
            else if (moviesViewModel.movies.value.movies != null)
                MovieList(
                    navHostController = navHostController,
                    sharedViewModel = sharedViewModel)

            }
        }
    }
}

@Composable
fun MovieList(
    navHostController: NavHostController,
    viewModel: MovieViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel) {

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
