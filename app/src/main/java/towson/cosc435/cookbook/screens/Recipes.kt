package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import towson.cosc435.cookbook.models.Ingredient
import towson.cosc435.cookbook.models.Recipe

@Composable
fun Recipes(navController: NavHostController) {
    var ingred1 = Ingredient("Apple", "Cups", "5")
    var ingred2 = Ingredient("Apple", "Cups", "5")
    var ingred3 = Ingredient("Apple", "Cups", "5")
    var ingred4 = Ingredient("Apple", "Cups", "5")
    var ingred5 = Ingredient("Apple", "Cups", "5")

    var ingredList: List<Ingredient> = listOf(ingred1, ingred2, ingred3, ingred4, ingred5)

    var testRecipe = Recipe(
        "Apple Pie",
        ingredList,
        "Peel apples and chop thin slices"
    )

    Column() {
        Row(
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Text(testRecipe.name)
        }
        Row(
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Text(testRecipe.ingredients.toString())
        }
        Row(
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Text(testRecipe.notes)
        }
    }
}