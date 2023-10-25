package com.example.presentation.navigation

sealed class Screen(val route:String){
    object Home : Screen("Home")
    object Details : Screen("Details")

}