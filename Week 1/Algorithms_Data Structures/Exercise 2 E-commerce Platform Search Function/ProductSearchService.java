import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductSearchService {

    private List<Product> products;

    public ProductSearchService(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public List<Product> search(SearchRequest request) {
        List<Product> results = new ArrayList<>(products);

        if (request.getQuery() != null && !request.getQuery().isEmpty()) {
            String lowerCaseQuery = request.getQuery().toLowerCase();
            results = results.stream()
                    .filter(p -> p.getName().toLowerCase().contains(lowerCaseQuery) ||
                            p.getDescription().toLowerCase().contains(lowerCaseQuery))
                    .collect(Collectors.toList());
        }

        if (request.getFilters() != null && !request.getFilters().isEmpty()) {
            for (Map.Entry<String, List<String>> entry : request.getFilters().entrySet()) {
                String filterType = entry.getKey();
                List<String> filterValues = entry.getValue().stream().map(String::toLowerCase).collect(Collectors.toList());

                results = results.stream()
                        .filter(p -> {
                            switch (filterType.toLowerCase()) {
                                case "category":
                                    return filterValues.contains(p.getCategory().toLowerCase());
                                case "brand":
                                    return filterValues.contains(p.getBrand().toLowerCase());
                                case "available":

                                    if (filterValues.contains("true") && p.isAvailable()) return true;
                                    if (filterValues.contains("false") && !p.isAvailable()) return true;
                                    return false;

                                default:
                                    return true;
                            }
                        })
                        .collect(Collectors.toList());
            }
        }

        if (request.getSortBy() != null && !request.getSortBy().isEmpty()) {
            switch (request.getSortBy().toLowerCase()) {
                case "priceasc":
                    results.sort(Comparator.comparingDouble(Product::getPrice));
                    break;
                case "pricedesc":
                    results.sort(Comparator.comparingDouble(Product::getPrice).reversed());
                    break;
                case "nameasc":
                    results.sort(Comparator.comparing(Product::getName));
                    break;
                case "namedesc":
                    results.sort(Comparator.comparing(Product::getName).reversed());
                    break;
                case "ratingdesc":
                    results.sort(Comparator.comparingInt(Product::getRating).reversed());
                    break;
                default:
                    break;
            }
        }

        int totalResults = results.size();
        int startIndex = (request.getPage() - 1) * request.getPageSize();
        int endIndex = Math.min(startIndex + request.getPageSize(), totalResults);

        if (startIndex < 0 || startIndex >= totalResults) {
            return Collections.emptyList();
        }

        return results.subList(startIndex, endIndex);
    }
}