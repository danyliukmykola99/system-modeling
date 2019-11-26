package danyliuk.mykola.model;

/**
 * @author Mykola Danyliuk
 */
public class Place {

    private String name;
    private Integer quantity;
    private Integer maxQuantity;

    public Place(String name, Integer quantity, Integer maxQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
    }

    public Place(String name, Integer quantity) {
        this(name, quantity, 0);
    }

    public Place(String name) {
        this(name, 0, 0);
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
