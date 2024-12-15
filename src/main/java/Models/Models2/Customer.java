package Models.Models2;

public class Customer {
    private Integer ID;
    private String Name;
    private String TypeBuyer;
    private String New;
    private String Regular;
    private String Vip;


    public Customer(Integer ID, String Name, String TypeBuyer) {
        this.ID = ID;
        this.Name = Name;
        this.TypeBuyer = TypeBuyer;
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
}
