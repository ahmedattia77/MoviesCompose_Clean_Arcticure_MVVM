package com.example.presentation.ui.uiComponent

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircleProgressbar () {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight().fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 28.dp),
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        Text(text = "Loading...")
    }
}