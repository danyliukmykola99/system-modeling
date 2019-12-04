package danyliuk.mykola.model;

import java.util.function.Function;

/**
 * @author Mykola Danyliuk
 */
public class TestArc extends InputArc {

    public TestArc(Place place, Transition transition, Integer quantity) {
        super(place, transition, quantity);
    }

    public TestArc(Place place, Transition transition) {
        super(place, transition);
    }

    public void execute(){}

}
