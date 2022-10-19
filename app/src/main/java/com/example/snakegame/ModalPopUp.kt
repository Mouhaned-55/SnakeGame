package com.example.snakegame

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.snakegame.navigation.Screen

@Composable
fun ModalPopUp(
    navController: NavController,
    getScore: () -> Int,
    onClick: (Boolean) -> Unit,
) {
    val shouldShowDialog = remember { mutableStateOf(true) }

    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
            shouldShowDialog.value = false
            onClick(true)
        },
            title = { Text(text = "GAME OVER") },
            text = { Text(text = "Better luck next time \n Final score : ${getScore()}") },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                        onClick(true)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2D321D))

                ) {
                    Text(text = "Restart", color = Color.White)
                }
            }
        , dismissButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                        onClick(true)
                        navController.navigate(Screen.StartScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2D321D))

                ) {
                    Text(text = "Quit", color = Color.White)
                }
            }
        )
    }
}