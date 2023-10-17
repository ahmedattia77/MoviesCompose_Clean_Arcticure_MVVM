package com.example.presentation.ui.uiComponent


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
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
            .height(220.dp)
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

    Spacer(modifier = Modifier.height(12.dp))

    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            repeat(images.size) {
                Box(
                    modifier = Modifier
                        .size(width = 40.dp, height = 8.dp)
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(3.dp)
                        )
                )
            }
        }

        Box(
            Modifier
                .slidingLineTransition(pagerState, 16f)
                .size(width = 40.dp, height = 8.dp)
                .background(
                    color = White,
                    shape = RoundedCornerShape(3.dp),
                )
        )
    }
}
@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.slidingLineTransition(pagerState: PagerState, distance: Float) =
    graphicsLayer {
        val scrollPosition = pagerState.currentPage + pagerState.currentPageOffsetFraction
        translationX = scrollPosition * distance
    }