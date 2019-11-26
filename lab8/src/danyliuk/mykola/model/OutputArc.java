package danyliuk.mykola.model;

/**
 * @author Mykola Danyliuk
 */
public class OutputArc extends Arc {

    public OutputArc(Transition transition, Place place, Integer quantity) {
        super(place, transition, quantity);
    }

    public OutputArc(Transition transition, Place place) {
        this(transition, place, 1);
    }

    public void execute(){
        int oldPlaceQuantity = place.getQuantity();
        place.setQuantity(oldPlaceQuantity + quantity);
    }

}
