package towson.cosc435.cookbook.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class RecipeRepo(private val recipeDao: RecipeDao) {
    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertRecipe(newrecipe: Recipe) {
        coroutineScope.launch(Dispatchers.IO) {
            recipeDao.insertRecipe(newrecipe)
        }
    }

    fun asyncFindRecipe(name: String): Deferred<List<Recipe>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async recipeDao.findRecipe(name)
        }

    fun deleteRecipe(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            recipeDao.deleteRecipe(name)
        }
    }

}