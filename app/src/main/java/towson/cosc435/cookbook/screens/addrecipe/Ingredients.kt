package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes

@Composable
fun Ingredients(navController: NavController, jsonString: String) {

    var recipeIngredients by remember { mutableStateOf("") }

    val onIngredientsTextChange = { text: String ->
        recipeIngredients = text
    }

    var jsonString = jsonString

    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)

    recipe = Recipe(recipe?.recipeName!!, recipe.recipeServings, recipe.recipeMinutes, recipeIngredients, "")
    val jsonWithIngredients = Gson().toJson(recipe)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier =
    Modifier.padding(50.dp)) {
        item {
            OutlinedTextField(
                label = { Text("Ingredients") },
                value = recipeIngredients,
                onValueChange = onIngredientsTextChange,
                singleLine = false
            )
        }
        item {
            Button(onClick = {
                navController.navigate(NavRoutes.Notes.route+ "/$jsonWithIngredients") }) {
                Text("Proceed")
            }
        }
        //item {
          //  Text(jsonString)
        //}

        item {

        }
    }
}