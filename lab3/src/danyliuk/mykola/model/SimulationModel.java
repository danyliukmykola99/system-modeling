package danyliuk.mykola.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class SimulationModel {

    private Model model;

    public SimulationModel(double createDelay, double processDelay, double cp1Probability, double p1p2Probability,
                           double p1p3Probability, double p3p4Probability, double p4p1Probability) {

        Create c = new Create("CREATE", createDelay);
        Process p1 = new Process("PROCESS 1", processDelay);
        Process p2 = new Process("PROCESS 2", processDelay);
        Process p3 = new Process("PROCESS 3", processDelay);
        Process p4 = new Process("PROCESS 4", processDelay);

        c.addNextElement(p1 ,cp1Probability);
        p1.addNextElement(p2, p1p2Probability);
        p1.addNextElement(p3, p1p3Probability);
        p3.addNextElement(p4, p3p4Probability);
        p4.addNextElement(p1, p4p1Probability);

        List<Element> list = Arrays.asList(c, p1, p2, p3, p4);
        model = new Model(list);
    }

    public void run(){
        model.simulate(1000.0);
    }
}
