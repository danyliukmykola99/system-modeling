package danyliuk.mykola;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mykola Danyliuk
 */
public class Model {

    private List<Transition> transitions;

    public Model(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public void runTransitions(){
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(transitions.size());
            executorService.invokeAll(transitions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private boolean isPresentEnabledTransition(){
        for (Transition t : transitions) {
            if (t.isEnabled()) {
                return true;
            }
        }
        return false;
    }

}