package danyliuk.mykola;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mykola Danyliuk
 */
public class Model {

    private List<Transition> transitions;
    private List<Place> places;
    private ExecutorService executorService;

    public Model(List<Transition> transitions, List<Place> places) {
        this.transitions = transitions;
    }

    public void execute(){
        executorService = Executors.newFixedThreadPool(transitions.size());
        transitions.forEach(t -> executorService.submit(t));
        while(true){
            if(!isPresentEnabledTransition()){
                executorService.shutdown();
                return;
            }
            printCurrentStatus();
            try {
                Thread.sleep(5000);
                printCurrentStatus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public void printCurrentStatus(){
        System.out.println("-----------------------");
        places.forEach(System.out::println);
    }

}