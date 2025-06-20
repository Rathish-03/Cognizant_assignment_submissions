import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private String description;
    private String category;
    private double price;
    private String brand;
    private boolean available;
    private int rating;

    public Product(String id, String name, String description, String category, double price, String brand, boolean available, int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.brand = brand;
        this.available = available;
        this.rating = rating;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getBrand() { return brand; }
    public boolean isAvailable() { return available; }
    public int getRating() { return rating; }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", available=" + available +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}