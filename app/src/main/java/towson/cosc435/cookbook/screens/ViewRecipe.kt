package towson.cosc435.cookbook.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes


@Composable
fun ViewRecipe(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
    jsonString: String
) {
    val API_URL = "https://source.unsplash.com/random/?"
    var url = API_URL + "shrug"
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)
    if (recipe != null) {
        val url = API_URL + recipe.recipeName.toString()
    }

    Column(
        modifier =
        Modifier.padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {

            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                if (recipe != null) {
                    Text(recipe.recipeName)
                }
            }

        }
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
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
                    if (painter.state is AsyncImagePainter.State.Loading || !viewModel.getConnection()) {
                        CircularProgressIndicator(Modifier)
                    }
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)

                    )
                }
            }


        }
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (recipe != null) {
                    Text("Time to prepare: " + recipe.recipeMinutes)
                }
            }

        }
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Ingredients:")
                }
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (recipe != null) {
                        Text(recipe.recipeIngredients)
                    }
                }
            }
        }
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (recipe != null) {
                    Text(recipe.recipeNotes)
                }
            }

        }
        Row(
            modifier =
            Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { navController.navigate(NavRoutes.EditRecipe.route + "/$jsonString") },
                modifier = Modifier.padding(10.dp)
            ) {
                Text("Edit")
            }

            Button(onClick = {
                if (recipe != null) {
                    viewModel.deleteRecipe(recipe.recipeName)
                }
                navController.navigate(NavRoutes.Cookbook.route)
            }, modifier = Modifier.padding(10.dp)) {
                Text("Delete")
            }

            Button(onClick = {}, modifier = Modifier.padding(10.dp)) {
                Text("Favorite")
            }
        }
    }

}






