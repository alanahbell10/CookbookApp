package towson.cosc435.cookbook

sealed class NavRoutes(val route: String) {
    object Welcome : NavRoutes("welcome")
    object AddRecipe : NavRoutes("add recipe")
    object Recipes : NavRoutes("recipes")
}