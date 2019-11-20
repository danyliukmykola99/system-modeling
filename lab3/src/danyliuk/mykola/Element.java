package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Element {
    private String name;
    protected double timeNext;
    private double delayMean, delayDev;
    private String distribution;
    protected int quantity; // кількість виконань події
    protected double tcurr;
    protected int state;
    protected Element nextElement;
    private static int nextId=0;
    private int id;

    public Element(double delay){
        name = "anonymus";
        timeNext = 0.0;
        delayMean = delay;
        distribution = "";
        tcurr = timeNext;
        state=0;
        nextElement=null;
        id = nextId;
        nextId++;
        name = "element"+id;
    }
    public Element(String nameOfElement, double delay){
        name = nameOfElement;
        timeNext = 0.0;
        delayMean = delay;
        distribution = "exp";
        tcurr = timeNext;
        state=0;
        nextElement=null;
        id = nextId;
        nextId++;
        name = "element"+id;
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

    public void setTcurr(double tcurr) {
        this.tcurr = tcurr;
    }

    public void setNextElement(Element nextElement) {
        this.nextElement = nextElement;
    }

    // вхід в елемент
    public void inAct() {}

    // вихід з елементу
    public void outAct(){
        quantity++;
    }

    public double getTimeNext() {
        return timeNext;
    }

    public int getId() {
        return id;
    }

    public void printResult(){
        System.out.println(name + "  quantity = "+ quantity);
    }

    public void printInfo(){
        System.out.println(name + " state= " +state+
                " quantity = "+ quantity+
                " tnext= "+ timeNext);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doStatistics(double delta){

    }
}
