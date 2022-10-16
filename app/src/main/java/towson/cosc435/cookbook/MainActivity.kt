package towson.cosc435.cookbook

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import towson.cosc435.cookbook.ui.theme.CookbookTheme
import java.util.*

val Red = Color(0xffff0000)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookbookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
@Composable
fun MainScreen() {
    Column {
        //Spacer(modifier = Modifier.height(Dp(10f)))
        //IngredientList()
        IngredientData()




    }
    // TODO - Recreate the screen in the lab description
    // TODO - Pick a Material color theme and apply it. (https://material.io/resources/color/)
}

@Composable
fun MeasurementSelect() {
    val measurementOptions = listOf("Cup", "Tsp", "Tbsp", "Quart", "Pint", "Ounce", "Gram", "Dash", "Pinch")
    val quantityOptions = listOf("1/8", "1/4", "1/3", "1/2", "1", "2", "3", "4", "5", "10")

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(measurementOptions[2]) }
    val (selectedOptionQ, onOptionSelectedQ) = remember { mutableStateOf(quantityOptions[2]) }
    Row() {
        Spacer(modifier = Modifier.width(Dp(50f)))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column {
                quantityOptions.forEach { text ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (text == selectedOptionQ),
                                onClick = { onOptionSelectedQ(text) }
                            )
                    ) {
                        val context = LocalContext.current
                        RadioButton(
                            selected = (text == selectedOptionQ),
                            onClick = { }
                        )
                        Text(
                            text = text,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(Dp(50f)))
        Column {
            measurementOptions.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                ) {
                    val context = LocalContext.current
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { }
                    )
                    Text(
                        text = text,
                    )
                }
            }
        }
    }
}

@Composable
fun IngredientData() {
// this variable use to handle list state
    val ingredientList = remember {
        mutableStateListOf<Ingredient>()
    }
// this variable use to handle edit text input value
    val name = remember { mutableStateOf(TextFieldValue()) }
    val quantity = remember { mutableStateOf(TextFieldValue()) }
    val measurement = remember { mutableStateOf(TextFieldValue()) }


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
                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = Black, fontSize = TextUnit.Unspecified,
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
                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = Black, fontSize = TextUnit.Unspecified,
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
                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = Black, fontSize = TextUnit.Unspecified,
                    fontFamily = FontFamily.SansSerif
                ),
                maxLines = 1,
                singleLine = true
            )
        }
        //MeasurementSelect()

        Button(
            onClick = {
                val newIngredient = Ingredient(name.value.text, measurement.value.text, quantity.value.text)
                ingredientList.add(newIngredient)
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
                                    color = Blue,
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
    }

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Enter recipe details:")
    }

}

@Composable
fun recipeName() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(value = "Recipe Name", onValueChange = { })
    }
}

@Composable
fun Ingredient() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(value = "Ingredient", onValueChange = { })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(value = "Quantity", onValueChange = { })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(value = "Measurement", onValueChange = { })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            addIngredientButton()
        }
    }
}

@Composable
fun addIngredientButton() {
    Row() {
        Button(
            onClick = { /* ... */ },
            // Uses ButtonDefaults.ContentPadding by default
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            )
        ) {
            Text("Add Ingredient")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CookbookTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainScreen()
        }
    }
}