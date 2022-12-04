package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes

//public var recipe = Recipe()
@Composable
fun ViewRecipe(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
    jsonString: String
) {

    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)

    Column(
        modifier =
        Modifier.padding(16.dp)) {
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()){

            Row(modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp)) {

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
                .fillMaxWidth()){
            Row(modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
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
                .fillMaxWidth()){

            Column(
                modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                    Text("Ingredients:")
                }
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
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
                .fillMaxWidth()){
            Row(modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                if (recipe != null) {
                    Text(recipe.recipeNotes)
                }
            }

        }
        Row(modifier =
        Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { navController.navigate(NavRoutes.EditRecipe.route+ "/$jsonString") }, modifier = Modifier.padding(10.dp)) {
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






