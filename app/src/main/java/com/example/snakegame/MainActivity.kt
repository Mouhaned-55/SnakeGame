package com.example.snakegame

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.example.snakegame.game.Snake
import com.example.snakegame.navigation.Navigation
import com.example.snakegame.ui.theme.SnakeGameTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game(lifecycleScope)


        setContent {
            SnakeGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0xFF8DA25A)
                ) {
                    Navigation(game)
                }
            }
        }
    }

    data class State(val food: Pair<Int, Int>, val snake: List<Pair<Int, Int>>)

    class Game(private val scope: CoroutineScope) {

        var success = true



        private val mutex = Mutex()
        private val mutableState =
            MutableStateFlow(State(food = Pair(5, 5), snake = listOf(Pair(7, 7))))

        val state: Flow<State> = mutableState

        var move = Pair(1, 0)
            set(value) {
                scope.launch {
                    field = value
                }
            }

        var score = 0
        var lastScore = 0

        init {
            scope.launch {
                var snakeLength = 4

                while (true) {
                    delay(150)
                    mutableState.update {
                        val newPosition = it.snake.first().let { poz ->
                            mutex.withLock {
                                Pair(
                                    (poz.first + move.first + BOARD_SIZE) % BOARD_SIZE,
                                    (poz.second + move.second + BOARD_SIZE) % BOARD_SIZE
                                )
                            }
                        }

                        if (newPosition == it.food) {
                            snakeLength++
                            score++
                        }
                        if (it.snake.contains(newPosition)) {
                            Log.d("score", "score: $score")
                            if (score > 0) {
                                lastScore = score
                            }
                            success = false
                            score = 0
                            snakeLength = 4
                        }

                        it.copy(
                            food = if (newPosition == it.food) Pair(
                                java.util.Random().nextInt(BOARD_SIZE),
                                java.util.Random().nextInt(BOARD_SIZE)
                            ) else it.food,
                            snake = listOf(newPosition) + it.snake.take(snakeLength - 1)
                        )
                    }
                }
            }
        }


        companion object {
            const val BOARD_SIZE = 16
        }


    }
}





