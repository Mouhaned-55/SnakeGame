package com.example.snakegame.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.snakegame.MainActivity
import com.example.snakegame.navigation.Screen

@Composable
fun StartScreen(
    navController: NavController, game: MainActivity.Game
) {
    Column(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(0.50f)
                .background(Color(0xFF8DA25A))
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Image(
                modifier = Modifier
                    .height(225.dp)
                    .width(225.dp),
                painter = painterResource(id = com.example.snakegame.R.drawable.snake3),
                contentDescription = "Snake Logo"
            )
            Text(
                text = "King Cobra",
                style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Get The Highest score \n And Beat Your Friends ",
                style = TextStyle(fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp), border = BorderStroke(2.dp, Color(0xFF2D321D)),
                backgroundColor = Color(0xFF8DA25A)
            ) {
                Column() {
                    Text(text = "High Score :", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 5.dp,top = 10.dp))
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(
                        text = "${game.lastScore}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(start = 40.dp, top = 5.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }
            Spacer(modifier = Modifier.height(130.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.Snake.route)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2D321D),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Start Game", fontSize = 20.sp)
            }


        }

    }

}