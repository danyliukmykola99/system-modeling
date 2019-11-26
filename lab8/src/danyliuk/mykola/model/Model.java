package danyliuk.mykola.model;

import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class Model {

    private List<Transition> transitions;
    private Double currentTime;
    private Double endTime;

    public Model(List<Transition> transitions) {
        this.transitions = transitions;
        this.currentTime = 0.0;
        this.endTime = 60.0;
    }

    public void run(){
        while (currentTime < endTime){
            transitions.forEach(t -> t.checkFinish(currentTime));
            transitions.forEach(t -> t.checkStart(currentTime));
            currentTime += 0.1;
        }
    }

}