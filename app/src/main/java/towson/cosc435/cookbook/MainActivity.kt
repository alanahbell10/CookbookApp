package towson.cosc435.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import towson.cosc435.cookbook.ui.theme.CookbookTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import towson.cosc435.cookbook.navigation.NavRoutes
import towson.cosc435.cookbook.screens.*
import towson.cosc435.cookbook.screens.AddRecipe
import towson.cosc435.cookbook.screens.Home
import towson.cosc435.cookbook.ui.theme.Teal200


val Teal = Color(0xFF009688)

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
    //val navController = rememberNavController()

    /*NavHost(
        navController = navController,
        startDestination = NavRoutes.AddRecipe.route
        //startDestination = NavRoutes.CookingTimer.route
    ) {
        composable(NavRoutes.Home.route) {
            Home()
        }
        composable(NavRoutes.Recipes.route) {
            Recipes(navController = navController)
        }
        composable(NavRoutes.AddRecipe.route) {
            AddRecipe(navController = navController)
        }
        composable(NavRoutes.ViewRecipe.route) {
            ViewRecipe(navController = navController)
        }
        composable(NavRoutes.CookingTimer.route) {
            CookingTimer(navController = navController)
        }
    }*/
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar() },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {

            }
        },
        backgroundColor = Color.Black
    )
    BottomNavBar()

}

@Composable
fun BottomNavBar() {
    val items = listOf(NavRoutes.Home, NavRoutes.Recipes, NavRoutes.AddRecipe)
    BottomNavigation(
        backgroundColor = Teal200,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = false,
                onClick = { /*TODO*/ })
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Cookbook", fontSize = 18.sp) },
        backgroundColor = Color.Magenta,
        contentColor = Color.Black
    )
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