package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson

import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes

@Composable
fun AddRecipe(navController: NavController) {

    var recipeName by remember { mutableStateOf("") }
    var recipeServings by remember { mutableStateOf("") }
    var recipeMinutes by remember { mutableStateOf("") }

    val onNameTextChange = { text : String ->
        recipeName = text
    }

    val onServingsTextChange = { text : String ->
        recipeServings = text
    }

    val onMinutesTextChange = { text : String ->
        recipeMinutes = text
    }

    val recipe = Recipe(
        recipeName,
        recipeServings,
        recipeMinutes,
        "",
        "")
    val jsonString = Gson().toJson(recipe)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier =
    Modifier.padding(50.dp)) {
        item {
            OutlinedTextField(
                label = { Text("Recipe Name") },
                value = recipeName,
                onValueChange = onNameTextChange,
                singleLine = true
            )
        }
        item {
            OutlinedTextField(
                label = { Text("Servings") },
                value = recipeServings,
                onValueChange = onServingsTextChange,
                singleLine = true
            )
        }
        item {
            OutlinedTextField(
                label = { Text("Minutes") },
                value = recipeMinutes,
                onValueChange = onMinutesTextChange,
                singleLine = true
            )
        }
        item {
            Button(onClick = {
                navController.navigate(NavRoutes.Ingredients.route + "/$jsonString") }) {
                Text("Proceed")
            }
        }
    }
}