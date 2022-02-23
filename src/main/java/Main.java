import com.sun.source.tree.Tree;

import java.util.*;

public class Main {


   public static void main(String[] args) {

      // Read all Customers @George
      final List<Customer> customers = FilesKt.readAllCustomers("c");
      System.out.printf("Read %s customers%n", customers.size());
        /*FilesKt.readAndDo("a", (liked, disliked) -> {
            //TODO: next version
        });*/

      // Rank their ingredients @Chris
      TreeMap<String,Integer> likedIngredients = getRankedIngredients(true, customers);
      TreeMap<String,Integer> dislikedIngredients = getRankedIngredients(false, customers);
      long maxIngredientCount = maxIngredientCount(customers);
      // Decide which ingredients to keep @Lef


   }


   private static TreeMap<String, Integer> getRankedIngredients(boolean likeable, List<Customer> customers) {
      TreeMap<String, Integer> res = new TreeMap<>();

      if (likeable) {
         customers.forEach(customer -> customer.getLikes()
                 .forEach(likedIngredient-> res.merge(likedIngredient, 1, Integer::sum)));
      }
      else {
         customers.forEach(customer -> customer.getDislikes()
                 .forEach(dislikedIngredient-> res.merge(dislikedIngredient, 1, Integer::sum)));
      }
      return res;
   }

   private static long maxIngredientCount(List<Customer> customers) {
      //Sorry for the one liner, it's been a while since I wrote java.
      return Collections.max(customers, Comparator.comparing(c-> c.getLikes().size())).getLikes().size();
   }
}
