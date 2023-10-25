package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.ui.screen.HomeScreen
import com.example.presentation.ui.screen.MovieDetails
import com.example.presentation.ui.viewModel.SharedViewModel

@Composable
fun setUpNavGraph(
    navHostController: NavHostController
){

    val sharedViewModel:SharedViewModel = viewModel()

    NavHost(navController = navHostController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen(navHostController = navHostController , sharedViewModel = sharedViewModel)
        }
        composable(route = Screen.Details.route){
            MovieDetails(navHostController , sharedViewModel = sharedViewModel)
        }
    }
}