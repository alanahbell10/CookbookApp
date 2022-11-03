package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun CookingTimer() {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val initialValue = 1f
        var value by remember { mutableStateOf(initialValue) }

        val totalTime: Long = 10000
        var currentTime by remember { mutableStateOf(totalTime) }

        val enterTime = remember { mutableStateOf(TextFieldValue())}
        var timerRunning by remember { mutableStateOf(false) }


        Spacer(modifier = Modifier.padding(70.dp))

        Row(modifier = Modifier.padding(12.dp)) {
            TextField(value = enterTime.value, onValueChange = { enterTime.value = it })
        }

        Row() {
            Text(
                text = (currentTime / 1000L).toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        LaunchedEffect(key1 = currentTime, key2 = timerRunning) {
            if(currentTime > 0 && timerRunning) {
                delay(100L)
                currentTime -= 100L
                value = currentTime / totalTime.toFloat()
            }
        }

        Row() {
            Button(
                onClick = {
                    if(currentTime <= 0L) {
                        currentTime = totalTime
                        timerRunning = true
                    } else {
                        timerRunning = !timerRunning
                    } },
                // change button color
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (!timerRunning || currentTime <= 0L) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                )
            ) {
                Text(
                    // change the text of button based on values
                    text = if (timerRunning && currentTime >= 0L) "Stop"
                    else if (!timerRunning && currentTime >= 0L) "Start"
                    else "Restart"
                )
            }
        }
    }
}


