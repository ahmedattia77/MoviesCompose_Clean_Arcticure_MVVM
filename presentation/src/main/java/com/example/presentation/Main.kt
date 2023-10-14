package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.presentation.ui.screen.HomeScreen
import com.example.presentation.ui.screen.MovieDetails
import dagger.hilt.android.AndroidEntryPoint

class Main {

    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                HomeScreen()
            }
        }
    }
}