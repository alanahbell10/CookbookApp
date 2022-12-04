package towson.cosc435.cookbook.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
//help from https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class RecipeRepo(private val recipeDao: RecipeDao) {
    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertRecipe(newrecipe: Recipe) {
        coroutineScope.launch(Dispatchers.IO) {
            recipeDao.insertRecipe(newrecipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        coroutineScope.launch(Dispatchers.IO) {
            recipeDao.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            recipeDao.deleteRecipe(name)
        }
    }

    fun deleteRecipeById(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            recipeDao.deleteRecipeById(id)
        }
    }

}