import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcommerceSearchDemo {

    public static void main(String[] args) {

        List<Product> allProducts = Arrays.asList(
                new Product("P001", "Laptop Pro X", "High performance laptop", "Electronics", 1200.00, "TechBrand", true, 5),
                new Product("P002", "Mechanical Keyboard", "RGB gaming keyboard", "Electronics", 95.50, "GamerGear", true, 4),
                new Product("P003", "Bluetooth Mouse", "Wireless mouse", "Electronics", 25.00, "Logi", true, 3),
                new Product("P004", "The Great Novel", "Bestselling fiction book", "Books", 15.75, "LiteraryPress", true, 5),
                new Product("P005", "Fantasy Epic Series Vol 1", "Fantasy adventure book", "Books", 22.00, "MythicReads", false, 4),
                new Product("P006", "Smartphone Z", "Latest smartphone model", "Electronics", 800.00, "TechBrand", true, 5),
                new Product("P007", "Coffee Maker Deluxe", "Automatic coffee machine", "Home Appliances", 150.00, "BrewCo", true, 4),
                new Product("P008", "Ergonomic Office Chair", "Comfortable desk chair", "Furniture", 250.00, "SitWell", true, 3),
                new Product("P009", "Wireless Earbuds", "Noise-cancelling earbuds", "Electronics", 75.00, "AudioWave", true, 4),
                new Product("P010", "Kids Story Book", "Bedtime stories for kids", "Books", 8.99, "KidReads", true, 2)
        );

        ProductSearchService searchService = new ProductSearchService(allProducts);

        System.out.println("--- Scenario 1: Simple Search Query ---");
        SearchRequest request1 = SearchRequest.builder()
                .query("laptop")
                .build();
        List<Product> results1 = searchService.search(request1);
        printResults(results1);

        System.out.println("\n--- Scenario 2: Search with Category Filter and Price Sort (Asc) ---");
        SearchRequest request2 = SearchRequest.builder()
                .query("") // Empty query
                .addFilter("category", "Electronics")
                .sortBy("priceAsc")
                .build();
        List<Product> results2 = searchService.search(request2);
        printResults(results2);

        System.out.println("\n--- Scenario 3: Search with Multiple Filters (Brand, Availability) and Rating Sort (Desc) ---");
        SearchRequest request3 = SearchRequest.builder()
                .query("")
                .addFilter("brand", "TechBrand")
                .addFilter("available", "true")
                .sortBy("ratingDesc")
                .build();
        List<Product> results3 = searchService.search(request3);
        printResults(results3);

        System.out.println("\n--- Scenario 4: Search for Books, Sort by Price Desc, Page 1, Size 2 ---");
        SearchRequest request4 = SearchRequest.builder()
                .query("")
                .addFilter("category", "Books")
                .sortBy("priceDesc")
                .page(1)
                .pageSize(2)
                .build();
        List<Product> results4 = searchService.search(request4);
        printResults(results4);

        System.out.println("\n--- Scenario 5: Search for Books, Sort by Price Desc, Page 2, Size 2 ---");
        SearchRequest request5 = SearchRequest.builder()
                .query("")
                .addFilter("category", "Books")
                .sortBy("priceDesc")
                .page(2)
                .pageSize(2)
                .build();
        List<Product> results5 = searchService.search(request5);
        printResults(results5);
    }

    private static void printResults(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            products.forEach(System.out::println);
        }
    }
}