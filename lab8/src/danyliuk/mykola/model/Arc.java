package danyliuk.mykola.model;

/**
 * @author Mykola Danyliuk
 */
public abstract class Arc {

    public Arc(Place place, Transition transition, Integer quantity) {
        this.place = place;
        this.transition = transition;
        this.quantity = quantity;
    }

    protected Place place;
    protected Transition transition;
    protected Integer quantity;

}
