package towson.cosc435.cookbook.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

import androidx.navigation.NavHostController
import towson.cosc435.cookbook.Ingredient

import towson.cosc435.cookbook.NavRoutes
import towson.cosc435.cookbook.Recipe

@Composable
fun AddRecipe(navController: NavHostController) {
    Column() {
        var newRecipe = remember { mutableStateOf(Recipe()) }
        val recipeName = remember { mutableStateOf(TextFieldValue()) }
        val recipeNotes = remember { mutableStateOf(TextFieldValue()) }

        val ingredientList = remember {
            mutableStateListOf<Ingredient>()
        }
// this variable use to handle edit text input value
        val name = remember { mutableStateOf(TextFieldValue()) }
        val quantity = remember { mutableStateOf(TextFieldValue()) }
        val measurement = remember { mutableStateOf(TextFieldValue()) }


        Row(
            Modifier
                .fillMaxWidth()
                .height(Dp(60f))
        ) {
            TextField(
                value = recipeName.value, onValueChange = { recipeName.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Recipe Name") },
                textStyle = TextStyle(
                    color = Color.Black, fontSize = TextUnit.Unspecified,
                    fontFamily = FontFamily.SansSerif
                ),
                maxLines = 1,
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(Dp(10f)))
        Text("Add ingredients below: ")

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(Dp(60f))
            ) {
                TextField(
                    value = name.value, onValueChange = { name.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Ingredient Name") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = TextUnit.Unspecified,
                        fontFamily = FontFamily.SansSerif
                    ),
                    maxLines = 1,
                    singleLine = true
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(Dp(60f))
            ) {
                TextField(
                    value = quantity.value, onValueChange = { quantity.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Quantity  Ex. 1/8, 1/2, 1") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = TextUnit.Unspecified,
                        fontFamily = FontFamily.SansSerif
                    ),
                    maxLines = 1,
                    singleLine = true
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(Dp(60f))
            ) {
                TextField(
                    value = measurement.value, onValueChange = { measurement.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Measurement Unit  Ex. Cup, Gram, Tbsp") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = TextUnit.Unspecified,
                        fontFamily = FontFamily.SansSerif
                    ),
                    maxLines = 1,
                    singleLine = true
                )
            }

            Button(
                onClick = {
                    val newIngredient =
                        Ingredient(name.value.text, measurement.value.text, quantity.value.text)
                    ingredientList.add(newIngredient)
                    name.value = TextFieldValue("")
                    quantity.value = TextFieldValue("")
                    measurement.value = TextFieldValue("")
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "+")
            }
            Spacer(modifier = Modifier.height(Dp(1f)))

            Surface(modifier = Modifier.padding(all = Dp(5f))) {
                LazyColumn {
                    itemsIndexed(ingredientList) { index, ingredient ->
                        val annotatedText = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Delete")
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(Dp(50f))
                        ) {
                            Text(text = ingredient.toString())

                            ClickableText(
                                text = annotatedText, onClick = {

                                    ingredientList.remove(ingredient)

                                },
                                modifier = Modifier.weight(0.15f)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(Dp(10f)))
        Text("Add cooking notes below ")
        Row(
            Modifier
                .fillMaxWidth()
                .height(Dp(60f))
        ) {
            TextField(
                value = recipeNotes.value, onValueChange = { recipeNotes.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Recipe Notes") },
                textStyle = TextStyle(
                    color = Color.Black, fontSize = TextUnit.Unspecified,
                    fontFamily = FontFamily.SansSerif
                ),
                maxLines = 20,
                singleLine = false
            )
        }
        Button(
            onClick = {
                newRecipe.value.setName(recipeName.toString())
                newRecipe.value.setNotes(recipeNotes.toString())
                newRecipe.value.ingredients = ingredientList
                navController.navigate(NavRoutes.Recipes.route)
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Add Recipe")
        }
    }
}