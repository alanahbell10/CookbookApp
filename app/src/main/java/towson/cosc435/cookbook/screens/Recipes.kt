package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun Recipes() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(Dp(60f))
    ) {
        TextField(
            value = "", onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Recipe Name") },
            textStyle = TextStyle(
                color = Color.Black, fontSize = TextUnit.Unspecified,
                fontFamily = FontFamily.SansSerif
            ),
            maxLines = 1,
            singleLine = true
        )
    }
}