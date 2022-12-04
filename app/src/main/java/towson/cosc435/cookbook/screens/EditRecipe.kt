package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.navigation.NavController
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes

@Composable
fun EditRecipe(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
    jsonString: String
) {

    var name by remember { mutableStateOf("") }
    var servings by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }


    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)

    name = recipe?.recipeName.toString()
    servings = recipe?.recipeServings.toString()
    minutes = recipe?.recipeMinutes.toString()
    ingredients = recipe?.recipeIngredients.toString()
    notes = recipe?.recipeNotes.toString()

    val onNameTextChange = { text : String ->
        name = text
    }

    val onServingsTextChange = { text : String ->
        servings = text
    }

    val onMinutesTextChange = { text : String ->
        minutes = text
    }

    val onIngredientsTextChange = { text : String ->
        ingredients = text
    }

    val onNotesTextChange = { text : String ->
        notes = text
    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier =
    Modifier.padding(50.dp)) {
        item {
            if (recipe != null) {
                OutlinedTextField(label = { Text("Recipe Name") }, value = name, onValueChange = onNameTextChange)
            }
        }

        item {
            if (recipe != null) {
                OutlinedTextField(label = { Text("Servings") },value = servings, onValueChange = onServingsTextChange)
            }
        }

        item {
            if (recipe != null) {
                OutlinedTextField(label = { Text("Cook Time") },value = minutes, onValueChange = onMinutesTextChange)
            }
        }

        item {
            if (recipe != null) {
                OutlinedTextField(label = { Text("Ingredients") }, value = ingredients, onValueChange = onIngredientsTextChange)
            }
        }

        item {
            if (recipe != null) {
                OutlinedTextField(label = { Text("Directions") },value = notes, onValueChange = onNotesTextChange)
            }
        }

        item {
            Button( onClick = {
                val id = recipe?.recipeId

                recipe?.recipeName = name
                recipe?.recipeServings = servings
                recipe?.recipeMinutes = minutes
                recipe?.recipeIngredients = ingredients
                recipe?.recipeNotes = notes


                if (recipe != null) {
                    viewModel.insertRecipe(
                        Recipe(
                            recipe.recipeName,
                            recipe.recipeServings,
                            recipe.recipeMinutes,
                            recipe.recipeIngredients,
                            recipe.recipeNotes
                        )
                    )
                }
                if (id != null) {
                    viewModel.deleteRecipeById(id)
                }


                val newJsonString = Gson().toJson(recipe)
                navController.navigate(NavRoutes.ViewRecipe.route + "/$newJsonString") } ) {
                Text("Save")
            }
        }
    }
}
