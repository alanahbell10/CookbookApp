package towson.cosc435.cookbook.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe

@Composable
fun Cookbook(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
) {

    LazyColumn(
    Modifier
    .fillMaxWidth()
    .padding(10.dp)
    ) {
        val list = allRecipes

        item {
            TitleRow(head1 = "ID", head2 = "Recipe")
        }

        items(list) { recipe ->
            RecipeRow(id = recipe.recipeId, name = recipe.recipeName)
        }
    }
}

@Composable
fun TitleRow(
    head1: String,
    head2: String,
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        Text(
            head1, color = Color.White,
            modifier = Modifier.weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier.weight(0.1f)
        )
    }
}

@Composable
fun RecipeRow(
    id: Int,
    name: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier.weight(0.1f))
        Text(name, modifier = Modifier.weight(0.1f))
    }
}