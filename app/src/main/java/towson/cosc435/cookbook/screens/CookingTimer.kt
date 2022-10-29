package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import java.util.*

@Composable
fun CookingTimer(navController: NavHostController) {
    Column() {
        Timer()
    }
}

@Composable
fun Timer() {
    var hours = remember { mutableStateOf("00") }
    var minutes = remember { mutableStateOf("00") }
    var seconds = remember { mutableStateOf("00") }

    var currentState = remember { mutableStateOf(TimerState.Idle) }
}

enum class TimerState {
    Idle, Started, Stopped, Canceled
}