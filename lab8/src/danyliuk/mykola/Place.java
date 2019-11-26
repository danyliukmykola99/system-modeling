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
        final StringBuilder sb = new StringBuilder("Place{");
        sb.append("name='").append(name).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
