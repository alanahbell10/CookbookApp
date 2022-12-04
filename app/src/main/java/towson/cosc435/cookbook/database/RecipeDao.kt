package towson.cosc435.cookbook.database

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//help from https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

@Dao
interface RecipeDao {

    @Insert
    fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE recipeName = :name")
    fun findRecipe(name: String): List<Recipe>

    @Update
    fun updateRecipe(recipe: Recipe)

    @Query("DELETE FROM recipes WHERE recipeName = :name")
    fun deleteRecipe(name: String)

    @Query("DELETE FROM recipes WHERE recipeId = :id")
    fun deleteRecipeById(id: Int)
}