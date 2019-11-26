package danyliuk.mykola;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
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

    public Double getTime() {
        return time;
    }

    public double getStartTime() {
        return startTime;
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

    public void addTestArc(Place place, Function<Place,Boolean> function){
        testArcs.add(new TestArc(place, this, function));
    }

    public void executeIfInputArcsAreValid() throws InterruptedException {
        if(isPermitted()){
            inputArcs.forEach(InputArc::execute);
            working = true;
            System.out.println(OffsetTime.now().toLocalTime() + " " + name);
            working = false;
            Thread.sleep((long) (time*1000));
            outputArcs.forEach(OutputArc::execute);
        }
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

    public Boolean isPermitted(){
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