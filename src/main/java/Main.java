import java.util.*;

public class Main {


   public static void main(String[] args) {

      for (String dataset : Arrays.asList("a", "b", "c", "d", "e")) {
         // Read all Customers @George
         final List<Customer> customers = FilesKt.readAllCustomers(dataset);
         System.out.printf("Read %s customers for dataset %s%n", customers.size(), dataset);
        /*FilesKt.readAndDo("a", (liked, disliked) -> {
            //TODO: next version
        });*/

         // Rank their ingredients @Chris
         TreeMap<String, Integer> likedIngredients = getRankedIngredients(true, customers);
         TreeMap<String, Integer> dislikedIngredients = getRankedIngredients(false, customers);
         long maxIngredientCount = maxIngredientCount(customers);

         // Decide which ingredients to keep @Lef
         Set<String> res = new HashSet<>();

         customers.forEach(customer -> {
            customer.getLikes().stream()
                  .filter(ingredient -> likedIngredients.getOrDefault(ingredient, -1)
                        .compareTo(dislikedIngredients.getOrDefault(ingredient, -1)) > 0)
                  .forEach(res::add);
         });

         System.out.printf("%s %s%n", res.size(), String.join(" ", res));
      }
   }


   private static TreeMap<String, Integer> getRankedIngredients(boolean likeable, List<Customer> customers) {
      TreeMap<String, Integer> res = new TreeMap<>();

      if (likeable) {
         customers.forEach(customer -> customer.getLikes()
               .forEach(likedIngredient -> res.merge(likedIngredient, 1, Integer::sum)));
      } else {
         customers.forEach(customer -> customer.getDislikes()
               .forEach(dislikedIngredient -> res.merge(dislikedIngredient, 1, Integer::sum)));
      }
      return res;
   }

   private static long maxIngredientCount(List<Customer> customers) {
      //Sorry for the one liner, it's been a while since I wrote java.
      return Collections.max(customers, Comparator.comparing(c -> c.getLikes().size())).getLikes().size();
   }
}
