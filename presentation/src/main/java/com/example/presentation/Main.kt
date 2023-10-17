package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.setUpNavGraph
import com.example.presentation.ui.screen.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

class Main {

    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                val state = rememberNavController()
                setUpNavGraph(navHostController = state)
//                HomeScreen()
            }
        }
    }
}