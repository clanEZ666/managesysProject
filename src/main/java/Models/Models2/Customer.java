package Models.Models2;

public class Customer {
    private Integer ID;
    private String Name;
    private String TypeBuyer;
    private static int currentId = 0;


    public Customer(String name, String typeBuyer) {
        this.ID = ++currentId;
        this.Name = name;
        this.TypeBuyer = typeBuyer;
    }


    public static int getCurrentId() {
        return currentId;
    }

    public String getTypeBuyer() {
        return TypeBuyer;
    }

    public String getName() {
        return Name;
    }

    public Integer getID() {
        return ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTypeBuyer(String typeBuyer) {
        TypeBuyer = typeBuyer;
    }
}
