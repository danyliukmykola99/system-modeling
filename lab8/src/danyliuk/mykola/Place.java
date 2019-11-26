package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Place {

    private String name;
    private Integer quantity;

    public Place(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Place(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " " + quantity;
    }
}
