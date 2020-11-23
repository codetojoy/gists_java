
import java.util.*;
import java.text.*;
import java.util.stream.Collectors;

class Recipe {
    String recipeName;
    List<Ingredient> ingredients = new ArrayList<>();
    List<Ingredient> getIngredients() { return ingredients; }
}

class Ingredient {
    String ingredientName;
    Date useBy;
    public Date getUseBy() { return useBy; }
}

public class Example {
    Date parseDate(String date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);
    }

    Ingredient buildIngredient(String name, String date) throws Exception {
        Ingredient result = new Ingredient();
        result.ingredientName = name;
        result.useBy = parseDate(date);
        return result;
    }

    List<Recipe> buildRecipes() throws Exception {
        List<Recipe> recipes = new ArrayList<>();

        Recipe r1 = new Recipe();
        r1.recipeName = "Pasta"; 
        r1.ingredients.add(buildIngredient("Shells", "2020-11-20"));
        r1.ingredients.add(buildIngredient("Moz. cheese", "2020-12-20"));

        Recipe r2 = new Recipe();
        r2.recipeName = "Burger";
        r2.ingredients.add(buildIngredient("Burger Bun", "2020-12-20"));
        r2.ingredients.add(buildIngredient("Beef Patty", "2020-12-23"));
        r2.ingredients.add(buildIngredient("Tomato", "2020-12-23"));

        recipes.add(r1);
        recipes.add(r2);

        return recipes;
    }

    List<Recipe> filter(List<Recipe> recipes, Date lunchDate) {
        
        List<Recipe> filteredList = 
            recipes.stream()
                   .filter(r -> r.getIngredients()
                                 .stream()
                                 .filter(i -> i.getUseBy().before(lunchDate))
                                 .count() == 0)
                   .collect(Collectors.toList());

        return filteredList;
    }

    public static void main(String[] args) throws Exception {
        Example example = new Example();
        List<Recipe> recipes = example.buildRecipes();
        Date lunchDate = example.parseDate("2020-11-22");
        List<Recipe> filteredRecipes = example.filter(recipes, lunchDate);

        for (Recipe recipe : filteredRecipes) {
            System.out.println("TRACER name: " + recipe.recipeName + " #: " + 
                                    recipe.ingredients.size());
        }
        System.out.println("Ready.");
    }
}
