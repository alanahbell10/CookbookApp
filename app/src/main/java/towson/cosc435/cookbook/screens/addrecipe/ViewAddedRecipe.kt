package towson.cosc435.cookbook.screens.addrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes

@Composable
fun ViewAddedRecipe(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
    jsonString: String
) {
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)



    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(20.dp) )

        Text("Recipe Name: ${recipe?.recipeName}")
        Text("Servings: ${recipe?.recipeServings}")
        Text("Cook time: ${recipe?.recipeMinutes} minutes")
        Text("Ingredients: ${recipe?.recipeIngredients}")
        Text("Directions: ${recipe?.recipeNotes}")

        Spacer(modifier = Modifier.padding(20.dp) )

        Button( onClick = {navController.navigate(NavRoutes.ViewRecipe.route)} ) {
            Text("Add New Recipe")
        }

    }
}


