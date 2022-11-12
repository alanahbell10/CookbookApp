package towson.cosc435.cookbook.navigation

import towson.cosc435.cookbook.R

sealed class NavRoutes(var route: String, var icon: Int, var title: String) {
    object Home : NavRoutes("home", R.drawable.ic_home, "Home")
    object AddRecipe : NavRoutes("add recipe", R.drawable.ic_add, "Add Recipe")
    object Recipes : NavRoutes("recipes", R.drawable.ic_recipe, "My Recipes")
    object ViewRecipe : NavRoutes("view recipe", R.drawable.ic_home, "View Recipe")
    object CookingTimer : NavRoutes("timer", R.drawable.ic_timer, "Timer")
}
