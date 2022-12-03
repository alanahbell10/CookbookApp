package towson.cosc435.cookbook.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import towson.cosc435.cookbook.Teal
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes
import towson.cosc435.cookbook.ui.theme.Purple100
import towson.cosc435.cookbook.ui.theme.Teal200

@Composable
fun Cookbook(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
) {

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val list = allRecipes

        items(list) { recipe ->
            RecipeRow(recipe = recipe, navController = navController)
        }
    }
}


@Composable
fun RecipeRow(
    recipe: Recipe,
    navController: NavController
) {
    val jsonString = Gson().toJson(recipe)
    Card(modifier = Modifier.padding(10.dp).clickable { navController.navigate(NavRoutes.ViewRecipe.route + "/$jsonString" ) }, backgroundColor = Purple100) {
        Row(modifier = Modifier.padding(10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = recipe.recipeName)
            }
            Column(modifier = Modifier.padding(10.dp)) {

            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "Serves ${recipe.recipeServings}")
            }
        }
    }
}