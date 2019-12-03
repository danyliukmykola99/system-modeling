package danyliuk.mykola.impl;

import danyliuk.mykola.model.Model;
import danyliuk.mykola.model.Place;
import danyliuk.mykola.model.Transition;

import java.util.Arrays;
import java.util.List;

public class MessengerModel {

    private Place place3, place4, place5, place6, place7, place8, place9, place10, place11, place12, place13, place1,
            place2;
    private Transition transition3, transition4, transition5, transition6, transition7, transition8, transition9,
            transition10, transition1, transition2;
    private Model model;

    public MessengerModel(){
        place1 = new Place("Можливо з'явиться повідомлення у А", 1);
        place2 = new Place("Можливо з'явиться повідомлення у B", 1);
        place3 = new Place("А готовий до відправлення повідомлення");
        place4 = new Place("Повідомлення А відправлено до В");
        place5 = new Place("Повідомлення A отримано B");
        place6 = new Place("B відправив сигнал про успішне отримання повідомлення");
        place7 = new Place("А отримав сигнал про успішне отримання повідомлення");
        place8 = new Place("B готовий до відправлення повідомлення");
        place9 = new Place("Повідомлення B відправлено до A");
        place10 = new Place("Повідомлення B отримано A");
        place11 = new Place("A відправиви сигнал про успішне отримання повідомлення");
        place12 = new Place("B отримав сигнал про успішне отримання повідомлення");
        place13 = new Place("Немає тупикової ситуації", 1);

        transition1 = new Transition("Надходження повідомлення у вузол А", 4.0);
        transition2 = new Transition("Надходження повідомлення у вузол B", 5.0);
        transition3 = new Transition("Відправлення повідомлення вузлом А", 2.0);
        transition4 = new Transition("Отримання повідомлення вузлом B", 0.5);
        transition5 = new Transition("Відправлення сигналу про успішне отримання повідомлення вузлом B", 0.5);
        transition6 = new Transition("Отримання сигналу про успішне отримання повідомлення вузлом B", 0.5);
        transition7 = new Transition("Відправлення повідомлення вузлом B", 2.5);
        transition8 = new Transition("Отримання повідомлення вузлом A", 0.5);
        transition9 = new Transition("Відправлення сигналу про успішне отримання повідомлення вузлом A", 0.5);
        transition10 = new Transition("Отримання сигналу про успішне отримання повідомлення вузлом A", 0.5);
        List<Transition> transitions = Arrays.asList(transition3, transition4, transition5, transition6, transition7,
                transition8, transition9, transition10, transition1, transition2);

        transition1.addInputArc(place1);
        transition1.addOutputArc(place3);
        transition1.addOutputArc(place1);

        transition2.addInputArc(place2);
        transition2.addOutputArc(place8);
        transition2.addOutputArc(place2);

        transition3.addInputArc(place3);
        transition3.addInputArc(place13);
        transition3.addOutputArc(place4);
        transition3.addOutputArc(place13);

        transition4.addInputArc(place4);
        transition4.addOutputArc(place5);

        transition5.addInputArc(place5);
        transition5.addOutputArc(place6);

        transition6.addInputArc(place6);
        transition6.addOutputArc(place7);

        transition7.addInputArc(place8);
        transition7.addInputArc(place13);
        transition7.addOutputArc(place9);
        transition7.addOutputArc(place13);

        transition8.addInputArc(place9);
        transition8.addOutputArc(place10);

        transition9.addInputArc(place10);
        transition9.addOutputArc(place11);

        transition10.addInputArc(place11);
        transition10.addOutputArc(place12);

        model = new Model(transitions);
    }

    public void run(){
        model.run();
    }


}
