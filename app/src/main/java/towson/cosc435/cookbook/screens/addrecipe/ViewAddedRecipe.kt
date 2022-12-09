package towson.cosc435.cookbook.screens.addrecipe

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.navigation.NavRoutes

@Composable
fun ViewAddedRecipe(
    allRecipes: List<Recipe>,
    viewModel: CookbookViewModel,
    navController: NavController,
    jsonString: String
) {
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Recipe> = moshi.adapter(Recipe::class.java)
    var recipe = adapter.fromJson(jsonString)



    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(20.dp) )
        ConfirmDialog()
        Text("Recipe Name: ${recipe?.recipeName}")
        Text("Servings: ${recipe?.recipeServings}")
        Text("Cook time: ${recipe?.recipeMinutes} minutes")
        Text("Ingredients: ${recipe?.recipeIngredients}")
        Text("Directions: ${recipe?.recipeNotes}")

        Spacer(modifier = Modifier.padding(20.dp) )

        Button( onClick = {navController.navigate(NavRoutes.ViewRecipe.route)} ) {
            Text("Add New Recipe")
        }

    }
}
@Composable
fun ConfirmDialog() {
    val open = remember { mutableStateOf(true) }

    Box{
        val popupWidth = 300.dp
        val popupHeight = 125.dp

        if(open.value)
            Popup(
                alignment = Alignment.TopCenter,
                properties = PopupProperties()
            ){
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(popupWidth, popupHeight)
                        .border(
                            2.dp, color = Color.Black,
                            RoundedCornerShape(10.dp)
                        )
                        .background(color = Color.White)
                ){
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Row(
                            modifier = Modifier.padding(5.dp),
                            horizontalArrangement = Arrangement.End
                        ){
                            Text("X", modifier = Modifier.clickable{open.value = false})
                        }
                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text("You've Created a Recipe!")
                        }
                    }
                }
            }
    }
}

