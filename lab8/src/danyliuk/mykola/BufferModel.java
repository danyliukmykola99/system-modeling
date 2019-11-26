package danyliuk.mykola;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class BufferModel {

    private Place place1, place2, place3, place4, place5, place6, place7, place8;
    private Transition transition1, transition2, transition3, transition4, transition5;
    private Model model;
    private int n = 5;

    public BufferModel(){
        place1 = new Place("Можливо з'явиться нова задача", 1);
        place2 = new Place("Producer працює", 1);
        place3 = new Place("Consumer працює", 1);
        place4 = new Place("Черга задач");
        place5 = new Place("Оброблено задач");
        place6 = new Place("Producer виключений");
        place7 = new Place("Consumer виключений ");
        place8 = new Place("Система працює", 1);

        transition1 = new Transition("Надходження задачі", 1.0);
        transition2 = new Transition("Обробка задачі", 2.0);
        transition3 = new Transition("Виключення Producer");
        transition4 = new Transition("Виключення Consumer");
        transition5 = new Transition("Виключення Системи");
        List<Transition> transitions = Arrays.asList(transition1, transition2, transition3, transition4, transition5);

        transition1.addInputArc(place1);
        transition1.addTestArc(place2, place -> place.getQuantity() == 1);
        transition1.addOutputArc(place1);
        transition1.addOutputArc(place4);

        transition2.addTestArc(place3, place -> place.getQuantity() == 1);
        transition2.addInputArc(place4);
        transition2.addOutputArc(place5);

        transition3.addTestArc(place4, place -> place.getQuantity() == 0);
        transition3.addInputArc(place7);
        transition3.addOutputArc(place6);

        transition4.addTestArc(place4, place -> place.getQuantity() == n);
        transition4.addOutputArc(place5);

        transition5.addInputArc(place6);
        transition5.addInputArc(place7);
        transition5.addInputArc(place8);

        model = new Model(transitions);
    }

    public void run(){
        model.runTransitions();
    }

}
