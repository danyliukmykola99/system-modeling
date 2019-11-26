package danyliuk.mykola;

import java.util.Arrays;
import java.util.List;

public class MessengerModel {

    private Place place1, place2, place3, place4, place5, place6, place7, place8, place9, place10, place11, place00,
            place01;
    private Transition transition1, transition2, transition3, transition4, transition5, transition6, transition7,
            transition8, transition00, transition01;
    private Model model;

    public MessengerModel(){
        place00 = new Place("Можливо з'явиться повідомлення у А", 1);
        place01 = new Place("Можливо з'явиться повідомлення у B", 1);
        place1 = new Place("А готовий до відправлення повідомлення");
        place2 = new Place("Повідомлення А відправлено до В");
        place3 = new Place("Повідомлення A отримано B");
        place4 = new Place("B відправив сигнал про успішне отримання повідомлення");
        place5 = new Place("А отримав сигнал про успішне отримання повідомлення");
        place6 = new Place("B готовий до відправлення повідомлення");
        place7 = new Place("Повідомлення B відправлено до A");
        place8 = new Place("Повідомлення B отримано A");
        place9 = new Place("A відправиви сигнал про успішне отримання повідомлення");
        place10 = new Place("B отримав сигнал про успішне отримання повідомлення");
        place11 = new Place("Немає тупикової ситуації", 1);

        transition00 = new Transition("Надходження повідомлення у вузол А", 4.0);
        transition01 = new Transition("Надходження повідомлення у вузол B", 5.0);
        transition1 = new Transition("Відправлення повідомлення вузлом А", 2.0);
        transition2 = new Transition("Отримання повідомлення вузлом B", 0.5);
        transition3 = new Transition("Відправлення сигналу про успішне отримання повідомлення вузлом B", 0.5);
        transition4 = new Transition("Отримання сигналу про успішне отримання повідомлення вузлом B", 0.5);
        transition5 = new Transition("Відправлення повідомлення вузлом B", 2.5);
        transition6 = new Transition("Отримання повідомлення вузлом A", 0.5);
        transition7 = new Transition("Відправлення сигналу про успішне отримання повідомлення вузлом A", 0.5);
        transition8 = new Transition("Отримання сигналу про успішне отримання повідомлення вузлом A", 0.5);
        List<Transition> transitions = Arrays.asList(transition1, transition2, transition3, transition4, transition5,
                transition6, transition7, transition8, transition00, transition01);

        transition00.addInputArc(place00);
        transition00.addOutputArc(place1);
        transition00.addOutputArc(place00);

        transition01.addInputArc(place01);
        transition01.addOutputArc(place6);
        transition01.addOutputArc(place01);

        transition1.addInputArc(place1);
        transition1.addInputArc(place11);
        transition1.addOutputArc(place2);
        transition1.addOutputArc(place11);

        transition2.addInputArc(place2);
        transition2.addOutputArc(place3);

        transition3.addInputArc(place3);
        transition3.addOutputArc(place4);

        transition4.addInputArc(place4);
        transition4.addOutputArc(place5);

        transition5.addInputArc(place6);
        transition5.addInputArc(place11);
        transition5.addOutputArc(place7);
        transition5.addOutputArc(place11);

        transition6.addInputArc(place7);
        transition6.addOutputArc(place8);

        transition7.addInputArc(place8);
        transition7.addOutputArc(place9);

        transition8.addInputArc(place9);
        transition8.addOutputArc(place10);

        model = new Model(transitions);
    }

    public void run(){
        model.run();
    }


}
