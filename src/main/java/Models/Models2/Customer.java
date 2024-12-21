package Models.Models2;

public class Customer {

    private static int idCounter = 1; // Счетчик для уникальных ID
    private int id;
    private String name;
    private final CustomerType typeBuyer;

    public enum CustomerType {
        NEW,
        REGULAR,
        VIP
    }

    public Customer(String name, CustomerType typeBuyer) {
        this.id = idCounter++;
        this.name = name;
        this.typeBuyer = typeBuyer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Type: " + typeBuyer;
    }
}