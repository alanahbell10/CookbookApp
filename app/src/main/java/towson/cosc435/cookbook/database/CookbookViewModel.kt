package towson.cosc435.cookbook.database

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
//help from https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class CookbookViewModel(application: Application) : ViewModel() {
    val allRecipes: LiveData<List<Recipe>>
    private val repository: RecipeRepo
    private var connectionStatus = true
    init {
        val recipeDb = RecipeRoomDatabase.getInstance(application)
        val recipeDao = recipeDb.recipeDao()
        repository = RecipeRepo(recipeDao)

        allRecipes = repository.allRecipes

        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netreq = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        viewModelScope.launch {
            while (isActive) {
        cm.requestNetwork(netreq, object: ConnectivityManager.NetworkCallback() {

                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        val nc = cm.getNetworkCapabilities(network)
                        if (nc == null) {
                            connectionStatus = false
                        }
                    }

            override fun onLost(network: Network) {
                super.onLost(network)
                Log.d(ViewModel::class.java.simpleName, "Network unavailable")
                connectionStatus = false
            }
        })
                delay(60 * 1000L)
            }
        }
    }

    fun insertRecipe(recipe: Recipe) {
        repository.insertRecipe(recipe)
    }

    fun updateRecipe(recipe: Recipe) {
        repository.updateRecipe(recipe)
    }

    fun deleteRecipe(name: String) {
        repository.deleteRecipe(name)
    }

    fun deleteRecipeById(id: Int) {
        repository.deleteRecipeById(id)
    }
}