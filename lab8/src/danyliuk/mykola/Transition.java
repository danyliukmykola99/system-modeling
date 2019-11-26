package danyliuk.mykola;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class Transition implements Runnable {

    private String name;
    private Double time;
    private List<InputArc> inputArcs;
    private List<OutputArc> outputArcs;

    public Transition(String name, Double time) {
        this.name = name;
        this.time = time;
        this.inputArcs = new ArrayList<>();
        this.outputArcs = new ArrayList<>();
    }

    public Transition(String name) {
        this(name, 0.0);
    }

    public void addInputArc(InputArc arc){
        inputArcs.add(arc);
    }

    public void addOutputArc(OutputArc arc){
        outputArcs.add(arc);
    }

    public void executeIfEnabled() throws InterruptedException {
        if(isEnabled()){
            //System.out.println("Execute input arcs...");
            inputArcs.forEach(InputArc::execute);
            //System.out.println("Execute transition...");
            Thread.sleep((long) (time*1000));
            //System.out.println("Execute output arcs...");
            outputArcs.forEach(OutputArc::execute);
        }
    }

    public boolean isEnabled(){
        for(InputArc inputArc: inputArcs){
            if (!inputArc.isEnabled()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        while(true){
            try {
                executeIfEnabled();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}