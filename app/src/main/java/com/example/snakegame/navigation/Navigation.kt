package com.example.snakegame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.snakegame.MainActivity
import com.example.snakegame.game.Snake
import com.example.snakegame.game.StartScreen

@Composable
fun Navigation(game: MainActivity.Game) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(Screen.StartScreen.route) {
            StartScreen(navController, game = game)
        }
        composable(Screen.Snake.route) {
            Snake(navController = navController, game = game)
        }
    }

}