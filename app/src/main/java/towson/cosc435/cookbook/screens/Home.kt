package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import towson.cosc435.cookbook.models.Recipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home() {

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Card(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentSize(), elevation = 5.dp) {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                    Text("Welcome!")
                }
            }

        }
        item {
            Text("Your favorite Recipes: ")
            favoriteRecipeCard()
            favoriteRecipeCard()
            favoriteRecipeCard()
            Row() {
                Card(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentSize(), elevation = 5.dp) {
                    Row() {
                        Button(onClick = {
                        }) {
                            Text("Add New Recipe")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun favoriteRecipeCard() {
    var ingred1 = Ingredient("Apple", "Cups", "5")
    var ingred2 = Ingredient("Apple", "Cups", "5")
    var ingred3 = Ingredient("Apple", "Cups", "5")
    var ingred4 = Ingredient("Apple", "Cups", "5")
    var ingred5 = Ingredient("Apple", "Cups", "5")

    var ingredList: List<Ingredient> = listOf(ingred1, ingred2, ingred3, ingred4, ingred5)

    var testRecipe = Recipe(
        "Apple Pie",
        ingredList,
        "Peel apples and chop thin slices",
        120,
        8,
        false
    )

    Row() {
        Card(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentSize(), elevation = 5.dp) {
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentSize()) {
                Column() {
                    Text(text = testRecipe.name)
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column() {
                    Text(text = testRecipe.minutes.toString() + " minutes")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column() {
                    Button(onClick = { }) {
                        Text("View")
                    }
                }
            }
        }
    }
}



