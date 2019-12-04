package danyliuk.mykola.model;

import danyliuk.mykola.model.InputArc;
import danyliuk.mykola.model.OutputArc;
import danyliuk.mykola.model.Place;
import danyliuk.mykola.model.TestArc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author Mykola Danyliuk
 */
public class Transition{

    private String name;
    private Double time;
    private List<InputArc> inputArcs;
    private List<OutputArc> outputArcs;
    private List<TestArc> testArcs;
    private boolean working;
    private double startTime;

    public Transition(String name, Double time) {
        this.name = name;
        this.time = time;
        this.inputArcs = Collections.synchronizedList(new ArrayList<>());
        this.outputArcs = Collections.synchronizedList(new ArrayList<>());
        this.testArcs = Collections.synchronizedList(new ArrayList<>());
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

    public void addTestArc(Place place){
        testArcs.add(new TestArc(place, this));
    }

    public void addTestArc(Place place, Integer quantity){
        testArcs.add(new TestArc(place, this, quantity));
    }

    public void checkFinish(Double currentTime){
        if(working && time <= currentTime - startTime){
            System.out.printf("%3.1f %s%n", currentTime,  name);
            working = false;
            outputArcs.forEach(OutputArc::execute);
        }
    }

    public void checkStart(Double currentTime){
        if(!working && isPermitted()){
            working = true;
            inputArcs.forEach(InputArc::execute);
            startTime = currentTime;
        }
    }

    private boolean isPermitted(){
        for(InputArc inputArc: inputArcs){
            if (!inputArc.isValid()){
                return false;
            }
        }
        for(TestArc testArc: testArcs){
            if (!testArc.isValid()){
                return false;
            }
        }
        return true;
    }
}