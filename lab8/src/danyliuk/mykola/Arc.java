package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Arc {

    public Arc(Place place, Transition transition, Integer quantity) {
        this.place = place;
        this.transition = transition;
        this.quantity = quantity;
    }

    protected Place place;
    protected Transition transition;
    protected Integer quantity;

    public Place getPlace() {
        return place;
    }

    public Transition getTransition() {
        return transition;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
