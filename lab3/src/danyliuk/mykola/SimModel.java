package danyliuk.mykola;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class SimModel {

    public static void main(String[] args) {
        Create c = new Create("CREATOR", 2.0);
        Process p = new Process("PROCESSOR", 1.0);
        System.out.println("id0 = " + c.getId() + "   id1=" + p.getId());
        c.setNextElement(p);
        p.setMaxAllowedQueueLength(5);

        List<Element> list = Arrays.asList(c,p);
        Model model = new Model(list);
        model.simulate(1000.0);

    }
}