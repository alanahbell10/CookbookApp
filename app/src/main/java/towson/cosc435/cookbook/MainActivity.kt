package towson.cosc435.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import towson.cosc435.cookbook.ui.theme.CookbookTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import towson.cosc435.cookbook.navigation.NavRoutes
import towson.cosc435.cookbook.screens.*
import towson.cosc435.cookbook.screens.AddRecipe
import towson.cosc435.cookbook.screens.Home
import towson.cosc435.cookbook.ui.theme.Teal200
import androidx.navigation.Navigation
import towson.cosc435.cookbook.models.Recipe


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
    val navController = rememberNavController()


    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Nav(navController = navController)
            }

        },
        backgroundColor = Color.White
    )

}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(NavRoutes.Home, NavRoutes.Recipes, NavRoutes.AddRecipe, NavRoutes.CookingTimer)
    BottomNavigation(
        backgroundColor = Teal200,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = false,
                //help from https://developer.android.com/jetpack/compose/navigation
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Cookbook", fontSize = 18.sp) },
        backgroundColor = Teal200,
        contentColor = Color.White
    )
}


@Composable
fun Nav(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            Home()
        }
        composable(NavRoutes.Recipes.route) {
            Recipes(navController)
        }
        composable(NavRoutes.AddRecipe.route) {
            AddRecipe()
        }
        composable(NavRoutes.ViewRecipe.route) {
            ViewRecipe()
        }
        composable(NavRoutes.CookingTimer.route) {
            CookingTimer()
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