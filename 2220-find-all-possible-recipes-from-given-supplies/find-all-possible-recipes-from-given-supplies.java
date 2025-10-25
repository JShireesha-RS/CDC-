import java.util.*;

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // Step 1: Store initial supplies in a set
        Set<String> available = new HashSet<>(Arrays.asList(supplies));

        // Step 2: Keep track of recipes we can make
        List<String> createdRecipes = new ArrayList<>();

        // Step 3: Put all recipe indices in queue
        Queue<Integer> recipeQueue = new LinkedList<>();
        for (int i = 0; i < recipes.length; i++) {
            recipeQueue.offer(i);
        }

        // Step 4: Keep processing until no new recipes are made
        int lastSize = -1;
        while (available.size() > lastSize) {
            lastSize = available.size();

            int size = recipeQueue.size();
            for (int i = 0; i < size; i++) {
                int recipeIdx = recipeQueue.poll();
                boolean canCreate = true;

                // Check if all ingredients are available
                for (String ingredient : ingredients.get(recipeIdx)) {
                    if (!available.contains(ingredient)) {
                        canCreate = false;
                        break;
                    }
                }

                if (canCreate) {
                    // Add this recipe to available list (can be used as ingredient)
                    available.add(recipes[recipeIdx]);
                    createdRecipes.add(recipes[recipeIdx]);
                } else {
                    // Not ready yet, put back in queue
                    recipeQueue.offer(recipeIdx);
                }
            }
        }

        return createdRecipes;
    }
}
