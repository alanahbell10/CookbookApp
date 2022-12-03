package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import towson.cosc435.cookbook.database.Recipe

//public var recipe = Recipe()
@Composable
fun ViewRecipe(
    navController: NavController,
    jsonString: String
) {

    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)

    Column(
        modifier =
        Modifier.padding(16.dp)) {
        Row(
            modifier =
            Modifier.fillMaxWidth()){

            if (recipe != null) {
                Text(recipe.recipeName)
            }

        }
        Row(
            modifier =
            Modifier.fillMaxWidth()){
            if (recipe != null) {
                Text("Time to prepare: " + recipe.recipeMinutes)
            }

        }
        Row(
            modifier =
            Modifier.fillMaxWidth()){
            Text("Ingredients: ")
            Column(
                modifier = Modifier.fillMaxWidth()) {
                val ingreds = recipe?.let { listOf(it.recipeIngredients) }

                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)) {
                        Text(ingreds.toString())
                    }
                }
            }

        }
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp)
                .fillMaxWidth()){
            if (recipe != null) {
                Text(recipe.recipeNotes)
            }
        }


}






