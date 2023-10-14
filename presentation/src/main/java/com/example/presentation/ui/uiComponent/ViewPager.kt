package com.example.presentation.ui.uiComponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.domain.model.movieImages.BackdropModel
import com.example.presentation.utils.Constant

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager (images:List<BackdropModel>){
    val pagerState = rememberPagerState (initialPage = 1)

    HorizontalPager(
        pageCount = images.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
    ) { index ->
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constant.IMAGE_BASE + images[index].file_path)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .height(220.dp) ,
        )
    }
}