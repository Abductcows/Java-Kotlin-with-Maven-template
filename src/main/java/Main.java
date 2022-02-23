import java.util.List;

public class Main {


   public static void main(String[] args) {

      // Read all Customers @George
      final List<Customer> customers = FilesKt.readAllCustomers("a");
      System.out.printf("Read %s customers%n", customers.size());
        /*FilesKt.readAndDo("a", (liked, disliked) -> {
            //TODO: next version
        });*/

      // Rank their ingredients @Chris

      // Decide which ingredients to keep @Lef

   }
}
