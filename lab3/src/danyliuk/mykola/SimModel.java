package danyliuk.mykola;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class SimModel {

    public static void main(String[] args) {

        Create c = new Create("CREATE", 2.0);
        Process p1 = new Process("PROCESS 1", 1.0);
        Process p2 = new Process("PROCESS 2", 1.0);
        Process p3 = new Process("PROCESS 3", 1.0);
        Process p4 = new Process("PROCESS 4", 1.0);

        c.addNextElement(p1 ,0.5);
        p1.addNextElement(p2, 0.2);
        p1.addNextElement(p3, 0.8);
        p3.addNextElement(p4, 1.0);
        p4.addNextElement(p1, 0.5);

        List<Element> list = Arrays.asList(c, p1, p2, p3, p4);
        Model model = new Model(list);
        model.simulate(1000.0);

    }
}