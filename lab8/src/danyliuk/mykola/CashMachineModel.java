package danyliuk.mykola;

import java.util.Arrays;
import java.util.List;

public class CashMachineModel {

    private Place place1, place2, place3, place4, place5, place6;
    private Transition transition1, transition2, transition3, transition4;
    private Model model;

    public CashMachineModel(){
        place1 = new Place("Можливо, що клієнт з'явиться", 1);
        place2 = new Place("У черзі клієнтів є клієнт");
        place3 = new Place("Клієнт обслуговується");
        place4 = new Place("Банкомат вільний", 1);
        place5 = new Place("Є обслугований");
        place6 = new Place("Кількість обслугованих клієнтів");

        transition1 = new Transition("Клієнт підійшов до банкомату", 2.0);
        transition2 = new Transition("Клієнт почав виконувати операції", 5.0);
        transition3 = new Transition("Клієнт закінчив виконувати операції");
        transition4 = new Transition("Клієнт залишив баномат", 1.0);
        List<Transition> transitions = Arrays.asList(transition1, transition2, transition3, transition4);

        transition1.addInputArc(place1);
        transition1.addOutputArc(place1);
        transition1.addOutputArc(place2);

        transition2.addInputArc(place4);
        transition2.addInputArc(place2);
        transition2.addOutputArc(place3);

        transition3.addInputArc(place3);
        transition3.addOutputArc(place4);
        transition3.addOutputArc(place5);

        transition4.addInputArc(place5);
        transition4.addOutputArc(place6);

        model = new Model(transitions);
    }

    public void run(){
        model.runTransitions();
    }

}
