package towson.cosc435.cookbook.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.gson.Gson
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes
import towson.cosc435.cookbook.ui.theme.Purple100


@Composable
fun Cookbook(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
) {

    val mySortedList: List<Recipe> = allRecipes.sortedBy { it.recipeName }

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(mySortedList) { recipe ->
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
        val API_URL = "https://source.unsplash.com/random/?"
        var url = API_URL + recipe.recipeName.toString()
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    val context = LocalContext.current
                    val painter = rememberAsyncImagePainter(
                        remember(url) {
                            ImageRequest.Builder(context)
                                .data(url)
                                .diskCacheKey(url)
                                .memoryCacheKey(url)
                                .build()
                        }
                    )
                    if (painter.state is AsyncImagePainter.State.Loading) {
                        CircularProgressIndicator(Modifier)
                    }//progressindicator will show until imagepainter is loaded
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)

                    )
                }
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
}