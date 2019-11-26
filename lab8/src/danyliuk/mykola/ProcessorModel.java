package danyliuk.mykola;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class ProcessorModel {

    private Place place1, place2, place3, place4, place5, place6, place7, place8, place9, place10;
    private Transition transition1, transition2, transition3, transition4, transition5, transition6;
    private Model model;

    public ProcessorModel(){
        place1 = new Place("Можливо надійде завдання 1-го типу", 1);
        place2 = new Place("Черга завдань 1-го типу");
        place3 = new Place("Оброблено завдань 1-го типу");
        place4 = new Place("Можливо надійде завдання 2-го типу", 1);
        place5 = new Place("Черга завдань 2-го типу");
        place6 = new Place("Оброблено завдань 2-го типу");
        place7 = new Place("Можливо надійде завдання 3-го типу", 1);
        place8 = new Place("Черга завдань 3-го типу");
        place9 = new Place("Оброблено завдань 3-го типу");
        place10 = new Place("Кількість вільних процесорів", 6);

        transition1 = new Transition("Надходження завдання 1-го типу", 4.0);
        transition2 = new Transition("Оброблення завдання 1-го типу", 3.5);
        transition3 = new Transition("Надходження завдання 2-го типу", 2.5);
        transition4 = new Transition("Оброблення завдання 2-го типу", 2.0);
        transition5 = new Transition("Надходження завдання 3-го типу", 1.5);
        transition6 = new Transition("Оброблення завдання 3-го типу", 1.0);
        List<Transition> transitions = Arrays.asList(
                transition1, transition2, transition3, transition4, transition5, transition6);

        transition1.addInputArc(place1);
        transition1.addOutputArc(place1);
        transition1.addOutputArc(place2);

        transition2.addInputArc(place2);
        transition2.addInputArc(place10, 6);
        transition2.addOutputArc(place3);
        transition2.addOutputArc(place10, 6);

        transition3.addInputArc(place4);
        transition3.addOutputArc(place4);
        transition3.addOutputArc(place5);

        transition4.addInputArc(place5);
        transition4.addInputArc(place10, 3);
        transition4.addOutputArc(place6);
        transition4.addOutputArc(place10, 3);

        transition5.addInputArc(place7);
        transition5.addOutputArc(place7);
        transition5.addOutputArc(place8);

        transition6.addInputArc(place8);
        transition6.addInputArc(place10, 2);
        transition6.addOutputArc(place9);
        transition6.addOutputArc(place10, 2);

        model = new Model(transitions);
    }

    public void run(){
        model.run();
    }

}
