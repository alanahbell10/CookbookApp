package towson.cosc435.cookbook.models;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
     String name;
     List<Ingredient> Ingredients= new ArrayList<Ingredient>();
     String notes;
     int minutes;
     int servings;
     Boolean isFavorite;
     //Image mealPicture;



     public Recipe(String name, List<Ingredient> ingredients, String notes, int minutes, int servings, Boolean isFavorite) {
          this.name = name;
          Ingredients = ingredients;
          this.notes = notes;
          this.minutes = minutes;
          this.servings = servings;
          this.isFavorite = isFavorite;
     }

     public Recipe() {
          this.name = "";
          Ingredients = new ArrayList<Ingredient>();
          this.notes = "";
          this.minutes = 0;
          this.servings = 0;
          this.isFavorite = false;
     }

     public int getMinutes() {
          return minutes;
     }

     public void setMinutes(int minutes) {
          this.minutes = minutes;
     }

     public String getName() {
          return name;
     }
     public void setName(String name) {
          this.name = name;
     }

     public List<Ingredient> getIngredients() {
          return Ingredients;
     }
     public void setIngredients(List<Ingredient> ingredients) {
          this.Ingredients = ingredients;
     }

     public String getNotes() {
          return notes;
     }
     public void setNotes(String notes) {
          this.notes = notes;
     }

     public int getServings() {
          return servings;
     }

     public void setServings(int servings) {
          this.servings = servings;
     }

     public Boolean getFavorite() {
          return isFavorite;
     }

     public void setFavorite(Boolean favorite) {
          isFavorite = favorite;
     }

     @Override
     public String toString() {
          return "Recipe: name='" + name + '\'' +
                  ", Ingredients=" + Ingredients +
                  ", notes=" + notes;
     }
}
