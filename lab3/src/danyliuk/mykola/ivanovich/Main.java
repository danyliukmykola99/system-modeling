package danyliuk.mykola.ivanovich;

import danyliuk.mykola.Process;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class Main {
    public static void main(String[] args){
        Data data = new Data(0, 5);
        Processor p1 = new Processor("#1", data, 5, 2, 1.0, 1.0);
        Processor p2 = new Processor("#2", data, 5, 2, 1.0, 1.0);
        Processor p3 = new Processor("#3", data, 5, 2, 1.0, 1.0);
        Processor p4 = new Processor("#4", data, 5, 2, 1.0, 1.0);
        p1.setNextProcessors(Arrays.asList(p2, p3), Arrays.asList(0.3, 0.7));
        p3.setNextProcessors(Collections.singletonList(p4), Collections.singletonList(1.0));
        p4.setNextProcessors(Collections.singletonList(p1), Collections.singletonList(0.3));
        List<Processor> processors = Arrays.asList(p1, p2, p3 , p4);
        Simulator simulator = new Simulator(data, processors);
        simulator.start();
    }
}
