package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import towson.cosc435.cookbook.models.Ingredient
import towson.cosc435.cookbook.models.Recipe

//public var recipe = Recipe()
@Composable
fun ViewRecipe(navController: NavController) {
    var ingred1 = Ingredient("Apple", "Cups", "5")
    var ingred2 = Ingredient("Apple", "Cups", "5")
    var ingred3 = Ingredient("Apple", "Cups", "5")
    var ingred4 = Ingredient("Apple", "Cups", "5")
    var ingred5 = Ingredient("Apple", "Cups", "5")

    var ingredList: List<Ingredient> = listOf(ingred1, ingred2, ingred3, ingred4, ingred5)

    var recipe = Recipe(
        "Apple Pie",
        ingredList,
        "Peel apples and chop thin slices",
        120,
        8,
        false
    )

    val checkedState = remember { mutableStateOf(false) }

    Column(
        modifier =
        Modifier.padding(16.dp)) {
        Row(
            modifier =
            Modifier.fillMaxWidth()){
                Text(recipe.name)

        }
        Row(
            modifier =
            Modifier.fillMaxWidth()){
            Text("Time to prepare: " + recipe.minutes.toString())

        }
        Row(
            modifier =
            Modifier.fillMaxWidth()){
            Text("Ingredients: ")
            Column(
                modifier = Modifier.fillMaxWidth()) {
                val ingreds = listOf(recipe.ingredients)
                ingreds.forEach { ingred ->
                    Row(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)) {
                            Text(ingred.toString())
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
                    Text(recipe.notes)

            }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Favorite: ")
            Switch(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    recipe.favorite = it
                }
            )
        }
        Row() {
            Button(onClick = { navController.navigate("recipes") }) {
                Text("Back")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = { /*navController.navigate("edit recipe")*/ }) {
                Text("Edit")
            }
            Spacer(modifier = Modifier.padding(8.dp))

        }
        }
        }





