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
import androidx.navigation.NavHostController
import towson.cosc435.cookbook.models.Ingredient
import towson.cosc435.cookbook.models.Recipe

//public var recipe = Recipe()
@Composable
fun ViewRecipe() {
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
        120
    )
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

    }
}


