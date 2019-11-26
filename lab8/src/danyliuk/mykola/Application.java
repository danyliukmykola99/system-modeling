package danyliuk.mykola;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class Application {

    public static void main(String[] args){

        Place place1 = new Place("Можливо, що клієнт з'явиться", 1);
        Place place2 = new Place("У черзі клієнтів є клієнт");
        Place place3 = new Place("Клієнт обслуговується");
        Place place4 = new Place("Банкомат вільний", 1);
        Place place5 = new Place("Є обслугований");
        Place place6 = new Place("Кількість обслугованих клієнтів");
        List<Place> places = Arrays.asList(place1, place2, place3, place4, place5, place6);

        Transition transition1 = new Transition("Клієнт підійшов до банкомату", 2.0);
        Transition transition2 = new Transition("Клієнт почав виконувати операції", 5.0);
        Transition transition3 = new Transition("Клієнт закінчив виконувати операції");
        Transition transition4 = new Transition("Клієнт залишив баномат", 1.0);
        List<Transition> transitions = Arrays.asList(transition1, transition2, transition3, transition4);

        Arc arc1 = new InputArc(place1, transition1);
        Arc arc2 = new OutputArc(transition1, place1);
        Arc arc3 = new OutputArc(transition1, place2);
        Arc arc4 = new InputArc(place2, transition2);
        Arc arc5 = new OutputArc(transition2, place3);
        Arc arc6 = new InputArc(place3, transition3);
        Arc arc7 = new OutputArc(transition3, place4);
        Arc arc8 = new InputArc(place4, transition2);
        Arc arc9 = new OutputArc(transition3, place5);
        Arc arc10 = new InputArc(place5, transition4);
        Arc arc11 = new OutputArc(transition4, place6);

        Model model1 = new Model(transitions, places);
        model1.execute();

    }

}