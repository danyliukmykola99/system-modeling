package danyliuk.mykola;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykola Danyliuk
 */
public abstract class Element {
    protected String name;
    protected HashMap<Element, Double> nextElementsWithProbabilities;
    protected double timeNext;
    private double delayMean, delayDev;
    private String distribution;
    protected int quantity; // кількість виконань події
    protected Double currentTime;
    protected boolean working;

    public Element(String nameOfElement, double delay){
        this.name = nameOfElement;
        this.timeNext = this.delayDev = 0.0;
        this.delayMean = delay;
        this.distribution = "exp";
        this.nextElementsWithProbabilities = new HashMap<>();
    }

    public void setCurrentTime(Double currentTime) {
        this.currentTime = currentTime;
    }

    public void addNextElement(Element element, Double probability){
        nextElementsWithProbabilities.put(element, probability);
    }

    public Element getNextElement(){
        if(nextElementsWithProbabilities.isEmpty()){
            return null;
        }
        double randValue = Math.random();
        for(Map.Entry<Element, Double> entry: nextElementsWithProbabilities.entrySet()){
            randValue -= entry.getValue();
            if(randValue <= 0){
                return entry.getKey();
            }
        }
        return null;
    }

    public abstract void updateTimeNext();

    public void inActNextElement(){
        Element element = getNextElement();
        if(element != null){
            element.inAct();
        } else {
            System.out.println(name + " DISPOSE");
        }
    }

    public double getDelay() {
        double delay = delayMean;
        if ("exp".equalsIgnoreCase(distribution)) {
            delay = FunRand.Exp(delayMean);
        } else {
            if ("norm".equalsIgnoreCase(distribution)) {
                delay = FunRand.Norm(delayMean, delayDev);
            } else {
                if ("unif".equalsIgnoreCase(distribution)) {
                    delay = FunRand.Unif(delayMean, delayDev);
                } else {
                    if("".equalsIgnoreCase(distribution))
                        delay = delayMean;
                }
            }
        }
        return delay;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    // вхід в елемент
    public abstract void inAct();

    // вихід з елементу
    public void outAct(){
        System.out.printf("%3.3f OUT ACT %s%n",currentTime , name);
        quantity++;
        updateTimeNext();
        inActNextElement();
    }

    public double getTimeNext() {
        return timeNext;
    }

    public void printResult(){
        System.out.println("-----------  " + name + "  ----------");
        System.out.println("Кількість виконань події = " + quantity);
    }

    public abstract void printInfo();

    public String getName() {
        return name;
    }

    public abstract void doStatistics(double delta);
}
