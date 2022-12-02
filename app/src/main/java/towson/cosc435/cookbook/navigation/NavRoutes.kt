package towson.cosc435.cookbook.navigation

import towson.cosc435.cookbook.R

sealed class NavRoutes(var route: String, var icon: Int, var title: String) {
    object Home : NavRoutes("home", R.drawable.ic_home, "Home")
    object Recipes : NavRoutes("recipes", R.drawable.ic_recipe, "My Recipes")
    object ViewRecipe : NavRoutes("view recipe", R.drawable.ic_home, "View Recipe")
    object CookingTimer : NavRoutes("timer", R.drawable.ic_timer, "Timer")
    object Cookbook : NavRoutes("cookbook", R.drawable.ic_recipe, "Cookbook")

    //for adding a new recipe
    object AddRecipe : NavRoutes("addrecipe", R.drawable.ic_add, "Add Recipe")
    object Ingredients : NavRoutes("ingredients", R.drawable.ic_add, "Add Ingredients")
    object Notes : NavRoutes("notes", R.drawable.ic_add, "Add Notes")
    object ViewCookbook : NavRoutes("viewcookbook", R.drawable.ic_add, "View Cookbook")



}
