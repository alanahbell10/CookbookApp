package towson.cosc435.cookbook;

import android.media.Image;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
     String name;
     List<Ingredient> Ingredients= new ArrayList<Ingredient>();
     List<String> notes = new ArrayList<String>();
     //Image mealPicture;


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
          Ingredients = ingredients;
     }

     public List<String> getNotes() {
          return notes;
     }
     public void setNotes(List<String> notes) {
          this.notes = notes;
     }
     public void addNote(String note) {
          this.notes.add(note);
     }

     @Override
     public String toString() {
          return "Recipe: name='" + name + '\'' +
                  ", Ingredients=" + Ingredients +
                  ", notes=" + notes;
     }
}
