package Models.Models2;

public class Customer {
    private int id;
    private String name;
    private CustomerType type;

    public Customer(String name, CustomerType type) {
        this.name = name;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', type=" + type + "}";
    }

    public enum CustomerType {
        NEW, REGULAR, VIP
    }
}