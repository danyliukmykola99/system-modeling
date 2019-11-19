package danyliuk.mykola;

import java.util.ArrayList;

/**
 * @author Mykola Danyliuk
 */
public class Model {

    private ArrayList<Element> list;
    private double tnext, tcurr;
    private Element currentElement;

    public Model(ArrayList<Element> elements) {
        list = elements;
        tnext = 0.0;
        tcurr = 0.0;
    }


    public void simulate(double time) {

        while (tcurr < time) {
            findClosestElement(); // визначення найближчої подї
            list.forEach(e -> e.doStatistics(tnext - tcurr)); // просування часу
            tcurr = tnext;
            list.forEach(e -> e.setTcurr(tcurr));
            currentElement.outAct(); // здійснення відповідної події

            // здійснення відповідної події для всіх елементів, час наступної події яких співпадає з поточним моментом часу.
            for (Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct();
                }
            }
            printInfo();
        }
        printResult();
    }

    private void findClosestElement(){
        tnext = Double.MAX_VALUE;
        for (Element e : list) {
            if (e.getTnext() < tnext) {
                tnext = e.getTnext();
                currentElement = e;
            }
        }
        System.out.println("\nIt's time for event in " +
                currentElement.getName() +
                ", time =   " + tnext);
    }

    public void printInfo() {
        for (Element e : list) {
            e.printInfo();
        }
    }

    public void printResult() {
        System.out.println("\n-------------RESULTS-------------");
        for (Element e : list) {
            e.printResult();
            if (e instanceof Process) {
                ((Process) e).printProcessResult();
            }
        }
    }
}

