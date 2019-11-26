package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class InputArc extends Arc {

    public InputArc(Place place, Transition transition, Integer quantity) {
        super(place, transition, quantity);
        transition.addInputArc(this);
    }

    public InputArc(Place place, Transition transition) {
        this(place, transition, 1);
    }

    public boolean isEnabled(){
        return place.getQuantity() >= quantity;
    }

    public void execute(){
        //System.out.print("Input arc: NAME: " + place.getName());
        int oldPlaceQuantity = place.getQuantity();
        //System.out.print(" BEFORE: " + oldPlaceQuantity);
        place.setQuantity(oldPlaceQuantity - quantity);
        //System.out.println(" AFTER: " + place.getQuantity());
    }

}
