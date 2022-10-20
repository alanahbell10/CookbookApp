package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import towson.cosc435.cookbook.Ingredient
import towson.cosc435.cookbook.Recipe

@Composable
fun Recipes() {
    var ingred1 = Ingredient("Apple", "Cups", "5")
    var ingred2 = Ingredient("Apple", "Cups", "5")
    var ingred3 = Ingredient("Apple", "Cups", "5")
    var ingred4 = Ingredient("Apple", "Cups", "5")
    var ingred5 = Ingredient("Apple", "Cups", "5")

    var ingredList: List<Ingredient> = listOf(ingred1, ingred2, ingred3, ingred4, ingred5)

    var testRecipe = Recipe("Apple Pie", ingredList, "Peel apples and chop thin slices")

    Column() {
        Row(
        ) {
            Text(testRecipe.name)
        }
        Row(
        ) {
            Text(testRecipe.ingredients.toString())
        }
        Row(
        ) {
            Text(testRecipe.notes)
        }
    }
}