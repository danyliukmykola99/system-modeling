package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class InputArc extends Arc {

    public InputArc(Place place, Transition transition, Integer quantity) {
        super(place, transition, quantity);
    }

    public InputArc(Place place, Transition transition) {
        this(place, transition, 1);
    }

    public boolean isValid(){
        return place.getQuantity() >= quantity;
    }

    public synchronized void execute(){
        int oldPlaceQuantity = place.getQuantity();
        place.setQuantity(oldPlaceQuantity - quantity);
    }

}
