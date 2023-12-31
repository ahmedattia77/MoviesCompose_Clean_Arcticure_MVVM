package com.example.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.domain.model.movie.MovieItemModel
import com.example.domain.model.movieActors.CastModel
import com.example.presentation.R
import com.example.presentation.ui.uiComponent.CircleProgressbar
import com.example.presentation.ui.uiComponent.ImagePager
import com.example.presentation.ui.viewModel.MovieDetailsViewModel
import com.example.presentation.ui.viewModel.SharedViewModel
import com.example.presentation.utils.Constant

@Composable
fun MovieDetails(
    navHostController: NavHostController,
    detailsViewModel: MovieDetailsViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {

    detailsViewModel.setId(sharedViewModel.movie_id.value)
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (detailsViewModel.movieImages.value.isLoading
            && detailsViewModel.actorState.value.isLoading
            && detailsViewModel.movieState.value.isLoading
        ) {
            LaunchedEffect(Unit, block = {
                detailsViewModel.updateState()
            })
            CircleProgressbar()
        }

        if (detailsViewModel.movieImages.value.backdropList.isNotEmpty()) {
            ImagePager(detailsViewModel.movieImages.value.backdropList)
        }

        if (detailsViewModel.movieState.value.movieDetails != null) {
            val movieDetails = detailsViewModel.movieState.value.movieDetails
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movieDetails?.title!!,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.white),
                maxLines = 1,
                fontWeight = FontWeight.W600
            )

            Text(
                text = movieDetails?.release_date!!,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                color = Color.Gray,
                maxLines = 1,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movieDetails?.overview!!,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                color = Color.Gray,
                fontWeight = FontWeight.W500
            )
            Divider(
                color = Color.DarkGray, thickness = 2.dp,

                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement
                    .spacedBy(16.dp, alignment = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(items = movieDetails.genres, itemContent = { index ->
                    Text(
                        text = index.name, color = Color.White, fontSize = 16.sp,
                        fontWeight = FontWeight.W600
                    )
                })
            }

            Divider(
                color = Color.DarkGray, thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        if (detailsViewModel.actorState.value.cast.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Cast",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            Divider(
                color = Color.DarkGray, thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            val castList = detailsViewModel.actorState.value.cast
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement
                    .spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(items = castList, itemContent = { item ->
                    actorSingleItem(item = item)
                })
            }

            val moviesSimilar =
                detailsViewModel.movieSimilar.value.movies!!.flow.collectAsLazyPagingItems()

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Recommended",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            Divider(
                color = Color.DarkGray, thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement
                    .spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(moviesSimilar.itemCount) { index ->
                    similarSingleItem(item = moviesSimilar[index]!!, onclick = { movie ->
                        detailsViewModel.setId(movie.id)
                        detailsViewModel.updateState()
                    })
                }
            }
        }
    }
}

@Composable
fun actorSingleItem(item: CastModel) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(shape = RoundedCornerShape(12.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constant.IMAGE_BASE + item.profilePath)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(220.dp),
            )
        }
        Text(
            text = item.name,
            fontWeight = FontWeight.W600,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun similarSingleItem(
    item: MovieItemModel,
    onclick: ((movie: MovieItemModel) -> Unit)
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onclick(item) }
    ) {
        Card(shape = RoundedCornerShape(12.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constant.IMAGE_BASE + item.poster_path)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(220.dp))
        }
        if (item.title.length > 12)
            Text(
                text = item.title.substring(0..12),
                fontWeight = FontWeight.W600,
                color = Color.White,
                fontSize = 16.sp)
        else
            Text(
                text = item.title,
                fontWeight = FontWeight.W600,
                color = Color.White,
                fontSize = 16.sp)
    }
}