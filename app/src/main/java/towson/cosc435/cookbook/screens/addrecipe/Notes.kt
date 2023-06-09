package towson.cosc435.cookbook.screens.addrecipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes

@Composable
fun Notes(allRecipes: List<Recipe>, viewModel: CookbookViewModel, navController: NavController, jsonString: String) {

    var recipeNotes by remember { mutableStateOf("") }

    val onNotesTextChange = { text: String ->
        recipeNotes = text
    }
    var jsonString = jsonString

    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)

    recipe = Recipe(recipe?.recipeName!!, recipe.recipeServings, recipe.recipeMinutes, recipe.recipeIngredients, recipeNotes)
    val jsonWithNotes = Gson().toJson(recipe)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier =
    Modifier.padding(50.dp)) {
        item {
            OutlinedTextField(
                label = { Text("Directions") },
                value = recipeNotes,
                onValueChange = onNotesTextChange,
                singleLine = false
            )
        }
        item {
            Button(onClick = {
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
                navController.navigate(NavRoutes.ViewCookbook.route+ "/$jsonWithNotes")
            }) {
                Text("Add Recipe")
            }
        }
        //item {
          //  Text(jsonString.toString())
        //}
    }
}