package towson.cosc435.cookbook;

import android.media.Image;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
     String name;
     List<Ingredient> Ingredients= new ArrayList<Ingredient>();
     String notes;
     //Image mealPicture;


     public Recipe(String name, List<Ingredient> ingredients, String notes) {
          this.name = name;
          Ingredients = ingredients;
          this.notes = notes;
     }

     public Recipe() {
          this.name = "";
          Ingredients = new ArrayList<Ingredient>();
          this.notes = "";
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

     @Override
     public String toString() {
          return "Recipe: name='" + name + '\'' +
                  ", Ingredients=" + Ingredients +
                  ", notes=" + notes;
     }
}
