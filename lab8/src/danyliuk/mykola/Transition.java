package danyliuk.mykola;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Mykola Danyliuk
 */
public class Transition implements Callable<Void> {

    private String name;
    private Double time;
    private List<InputArc> inputArcs;
    private List<OutputArc> outputArcs;
    private Boolean working;

    public Transition(String name, Double time) {
        this.name = name;
        this.time = time;
        this.inputArcs = Collections.synchronizedList(new ArrayList<>());
        this.outputArcs = Collections.synchronizedList(new ArrayList<>());
    }

    public Transition(String name) {
        this(name, 0.0);
    }

    public void addInputArc(Place place, Integer quantity){
        inputArcs.add(new InputArc(place, this, quantity));
    }

    public void addInputArc(Place place){
        inputArcs.add(new InputArc(place, this));
    }

    public void addOutputArc(Place place, Integer quantity){
        outputArcs.add(new OutputArc(this, place, quantity));
    }

    public void addOutputArc(Place place){
        outputArcs.add(new OutputArc(this, place));
    }

    public void executeIfInputArcsAreValid() throws InterruptedException {
        if(isInputArcsValid()){
            inputArcs.forEach(InputArc::execute);
            working = true;
            System.out.println(OffsetTime.now().toLocalTime() + " " + name);
            working = false;
            Thread.sleep((long) (time*1000));
            outputArcs.forEach(OutputArc::execute);
        }
    }

    public Boolean isInputArcsValid(){
        for(InputArc inputArc: inputArcs){
            if (!inputArc.isValid()){
                return false;
            }
        }
        return true;
    }

    public boolean isEnabled(){
        return working || isInputArcsValid();
    }

    @Override
    public Void call() throws Exception {
        while(true){
            try {
                executeIfInputArcsAreValid();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}