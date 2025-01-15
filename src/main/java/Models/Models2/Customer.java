package Models.Models2;

public class Customer {
    private int id;
    private final String name;
    private final CustomerType customerType;

    public enum CustomerType {
        NEW,
        REGULAR,
        VIP
    }

    public Customer(String name, CustomerType customerType) {
        this.name = name;
        this.customerType = customerType;
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
        return customerType;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', type=" + customerType + "}";
    }

}