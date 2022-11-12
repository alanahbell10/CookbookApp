package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import java.math.RoundingMode
import java.util.*
import kotlin.concurrent.timer

fun getMinutes(totalTime: Long) : Long {
    var minutes: Long = 0
    minutes = (totalTime / 60000).toBigDecimal().setScale(1, RoundingMode.FLOOR).toLong()
    return minutes
}

fun getSeconds(totalTime: Long) : Long {
    var seconds: Long = 0
    var minRemainder = totalTime % 60000
    seconds = minRemainder / 1000
    return seconds
}
@Composable
fun CookingTimer() {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val initialValue = 1f
        var value by remember { mutableStateOf(initialValue) }

        val enterMins = remember { mutableStateOf("")}
        val enterSecs = remember { mutableStateOf("")}

        var timerRunning by remember { mutableStateOf(false) }

        val totalMins: Long = enterMins.value?.toLongOrNull() ?: 0
        val totalSecs: Long = enterSecs.value?.toLongOrNull() ?: 0

        var totalTime: Long = (totalMins * 60000) + (totalSecs * 1000)
        var currentTime by remember { mutableStateOf(totalTime) }


        Spacer(modifier = Modifier.padding(30.dp))

        getDisplayTime(currentTime = currentTime)


        LaunchedEffect(key1 = currentTime, key2 = timerRunning) {
            if(currentTime > 0 && timerRunning == true) {
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
                        MaterialTheme.colors.secondary
                    } else {
                        Color.Red
                    }
                )
            ) {
                Text(
                    // change the text of button based on values
                    text = if (timerRunning && currentTime >= 0L) "Stop"
                    else if (!timerRunning && currentTime >= 0L) "Start"
                    else "Restart", color = Color.White
                )
            }
        }
        Row() {
            Button(onClick = {
                currentTime = 0
                timerRunning = false
                enterMins.value = "0"
                enterSecs.value = "0"
            }) {
                Text("Restart")

            }
        }
        Spacer(modifier = Modifier.padding(36.dp))

        Column() {
            Row() {
                TextField(value = enterMins.value, onValueChange = { enterMins.value = it }, label = { Text(text = "Minutes")})
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row() {
                TextField(value = enterSecs.value, onValueChange = { enterSecs.value = it }, label = { Text(text = "Seconds")})
            }
        }


    }
}

@Composable
fun getDisplayTime(currentTime: Long) {

    Row(verticalAlignment = Alignment.Bottom) {
        Column() {
            Text(
                text = getMinutes(currentTime).toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        }
        Column() {
            Text(
                text ="M",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )

        }

        Spacer(modifier = Modifier.padding(4.dp))

        Column() {
            Text(
                text = getSeconds(currentTime).toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        }
        Column() {
            Row() {
                Text(
                    text ="S",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondary
                )
            }

        }
    }
}

@Composable
fun getTime(enterMins:  MutableState<String>, enterSecs: MutableState<String>) {




}




