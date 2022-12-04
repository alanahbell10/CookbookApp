package towson.cosc435.cookbook

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import towson.cosc435.cookbook.ui.theme.CookbookTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import towson.cosc435.cookbook.navigation.NavRoutes
import towson.cosc435.cookbook.screens.*
import towson.cosc435.cookbook.screens.AddRecipe
import towson.cosc435.cookbook.ui.theme.Teal200
import towson.cosc435.cookbook.database.CookbookViewModel
import towson.cosc435.cookbook.database.Recipe
import towson.cosc435.cookbook.screens.addrecipe.Notes
import towson.cosc435.cookbook.screens.addrecipe.ViewAddedRecipe


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

                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: CookbookViewModel = viewModel(
                            it,
                            "CookbookViewModel",
                            CookbookViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )

                        ScreenSetup(viewModel)

                    }
                }
            }
        }
    }

    @Composable
    fun ScreenSetup(viewModel: CookbookViewModel) {

        val allRecipes by viewModel.allRecipes.observeAsState(listOf())

        MainScreen(
            allRecipes = allRecipes,
            viewModel = viewModel
        )

    }

    @Composable
    fun MainScreen(
        allRecipes: List<Recipe>,
        viewModel: CookbookViewModel
    ) {
        val navController = rememberNavController()



        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavBar(navController) },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    NavHost(
                        navController,
                        startDestination = NavRoutes.Cookbook.route
                    ) {

                        composable(NavRoutes.Cookbook.route) {
                            Cookbook(allRecipes, viewModel, navController)
                        }
                        composable(NavRoutes.CookingTimer.route) {
                            CookingTimer()
                        }

                        composable(NavRoutes.ViewRecipe.route + "/{jsonString}") { backStackEntry ->
                            val jsonString = backStackEntry.arguments?.getString("jsonString")
                            ViewRecipe(allRecipes, viewModel, navController = navController, jsonString = jsonString.toString())
                        }

                        //add new recipe navigation
                        composable(NavRoutes.ViewCookbook.route + "/{jsonWithNotes}") { backStackEntry ->
                            val newRecipe = backStackEntry.arguments?.getString("jsonWithNotes")
                            ViewAddedRecipe(allRecipes, viewModel, navController, newRecipe.toString())
                        }
                        composable(NavRoutes.AddRecipe.route) {
                            AddRecipe(navController = navController)
                        }
                        composable(NavRoutes.Ingredients.route + "/{jsonString}") { backStackEntry ->
                            val jsonString = backStackEntry.arguments?.getString("jsonString")
                            Ingredients(navController = navController, jsonString.toString())
                        }
                        composable(NavRoutes.Notes.route + "/{jsonWithIngredients}") { backStackEntry ->
                            val jsonString = backStackEntry.arguments?.getString("jsonWithIngredients")
                            Notes(allRecipes, viewModel, navController = navController, jsonString.toString())
                        }

                        //edit recipe
                        composable(NavRoutes.EditRecipe.route + "/{jsonString}") { backStackEntry ->
                            val jsonString = backStackEntry.arguments?.getString("jsonString")
                            EditRecipe(allRecipes, viewModel, navController = navController, jsonString = jsonString.toString())
                        }
                    }
                }

            },
            backgroundColor = Color.White
        )

    }

    @Composable
    fun BottomNavBar(navController: NavController) {
        val items =
            listOf(NavRoutes.Cookbook, NavRoutes.AddRecipe, NavRoutes.CookingTimer)
        BottomNavigation(
            backgroundColor = Teal200,
            contentColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
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

}

    class CookbookViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CookbookViewModel(application) as T
        }
    }