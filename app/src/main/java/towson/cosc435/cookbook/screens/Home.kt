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

}



