package com.example.snakegame.navigation

sealed class Screen(val route: String) {

    object StartScreen : Screen("start_Screen")

    object Snake : Screen("game_screen")

}