package towson.cosc435.cookbook.database

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CookbookViewModel(application: Application) : ViewModel() {
    val allRecipes: LiveData<List<Recipe>>
    private val repository: RecipeRepo

    init {
        val recipeDb = RecipeRoomDatabase.getInstance(application)
        val recipeDao = recipeDb.recipeDao()
        repository = RecipeRepo(recipeDao)

        allRecipes = repository.allRecipes
    }

    fun insertRecipe(recipe: Recipe) {
        repository.insertRecipe(recipe)
    }

    fun deleteRecipe(name: String) {
        repository.deleteRecipe(name)
    }
}