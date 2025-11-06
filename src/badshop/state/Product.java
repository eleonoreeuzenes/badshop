package badshop.state;

public class Product {
    public String id;
    public String name;
    public String description;
    public Double price;

    public Product(String id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}