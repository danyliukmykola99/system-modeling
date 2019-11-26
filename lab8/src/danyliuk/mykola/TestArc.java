package danyliuk.mykola;

import java.util.function.Function;

/**
 * @author Mykola Danyliuk
 */
public class TestArc extends Arc {

    private Function<Place,Boolean>  function;

    public TestArc(Place place, Transition transition, Function<Place,Boolean> function) {
        super(place, transition, 1);
        this.function = function;
    }

    public Boolean isValid(){
        return function.apply(place);
    }

}
