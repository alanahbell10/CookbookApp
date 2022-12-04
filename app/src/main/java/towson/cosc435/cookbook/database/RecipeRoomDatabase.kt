package towson.cosc435.cookbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//help from https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeRoomDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
        companion object {
            private var INSTANCE: RecipeRoomDatabase? = null

            fun getInstance(context: Context): RecipeRoomDatabase {
                synchronized(this) {
                    var instance = INSTANCE
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            RecipeRoomDatabase::class.java,
                            "recipe_database"
                        ).fallbackToDestructiveMigration().build()

                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
}