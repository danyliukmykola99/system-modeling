package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class OutputArc extends Arc {

    public OutputArc(Transition transition, Place place,  Integer quantity) {
        super(place, transition, quantity);
        transition.addOutputArc(this);
    }

    public OutputArc(Transition transition, Place place) {
        this(transition, place, 1);
    }

    public void execute(){
        //System.out.print("Output arc: NAME: " + place.getName());
        int oldPlaceQuantity = place.getQuantity();
        //System.out.print(" BEFORE: " + oldPlaceQuantity);
        place.setQuantity(oldPlaceQuantity + quantity);
        //System.out.println(" AFTER: " + place.getQuantity());
    }

}
