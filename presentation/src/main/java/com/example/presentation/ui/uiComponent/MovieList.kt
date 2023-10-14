package com.example.presentation.ui.uiComponent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.movie.MovieItemModel
import com.example.presentation.ui.viewModel.MovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MovieList (
    movieViewModel: MovieViewModel = hiltViewModel()) {


    if (movieViewModel.state.value.isLoading)
        CircleProgressbar()

    val movies = movieViewModel.state.value.movies!!.flow.collectAsLazyPagingItems()

    /*
    if (movieViewModel.state.value.error.isEmpty()) {
        LazyColumn(
            content = {
                items(movies.itemCount) { index ->
                    Card(
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize(),
                        elevation = 15.dp,
                    ) {

                        MovieItem(movies[index]!!)
                    }
                }
            }
        )
    }

     */
}

@Composable
fun MovieItem (movie : MovieItemModel){
    Text(text = movie.title)
    Text(text = movie.release_date)
    Text(text = movie.id.toString())
}