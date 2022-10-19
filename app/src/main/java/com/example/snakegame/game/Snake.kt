package com.example.snakegame.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snakegame.MainActivity
import com.example.snakegame.ModalPopUp
import com.example.snakegame.ui.theme.DarkGreen
import com.example.snakegame.ui.theme.Shapes


@Composable
fun Snake(
    navController: NavController,
    game: MainActivity.Game
) {


    val state = game.state.collectAsState(initial = null)
    if (!game.success) {
        ModalPopUp(navController = navController, getScore = { game.lastScore }, onClick = {
            game.success = it
        })
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        state.value?.let {
            Board(it)
        }
        Buttons {
            game.move = it
        }
        Card(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp), border = BorderStroke(2.dp, Color(0xFF2D321D)),
            backgroundColor = Color(0xFF8DA25A)
        ) {
            Column() {

                Text(
                    text = "${game.score}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(start = 30.dp, top = 10.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Composable
fun Buttons(onDirectionChange: (Pair<Int, Int>) -> Unit) {
    val buttonSize = Modifier.size(64.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        Button(
            onClick = {
                onDirectionChange(Pair(0, -1))
            },
            modifier = buttonSize
                .height(50.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF2D321D),
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.KeyboardArrowUp, null)
        }
        Row {
            Button(
                onClick = {
                    onDirectionChange(Pair(-1, 0))
                },
                modifier = buttonSize
                    .height(50.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2D321D),
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.KeyboardArrowLeft, null)
            }
            Spacer(modifier = buttonSize)
            Button(
                onClick = {
                    onDirectionChange(Pair(1, 0))
                },
                modifier = buttonSize
                    .height(50.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2D321D),
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
        }

        Button(
            onClick = {
                onDirectionChange(Pair(0, 1))
            },
            modifier = buttonSize
                .height(50.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF2D321D),
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.KeyboardArrowDown, null)
        }
    }
}


@Composable
fun Board(state: MainActivity.State) {
    BoxWithConstraints(Modifier.padding(16.dp)) {
        val tileSize = maxWidth / MainActivity.Game.BOARD_SIZE

        Box(
            Modifier
                .size(maxWidth)
                .border(2.dp, Color.DarkGray)
        )

        Box(
            Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(
                    DarkGreen, CircleShape
                )
        )

        state.snake.forEach {
            Box(
                modifier = Modifier
                    .offset(x = tileSize * it.first, y = tileSize * it.second)
                    .size(tileSize)
                    .background(
                        DarkGreen, Shapes.small
                    )
            )
        }
    }
}