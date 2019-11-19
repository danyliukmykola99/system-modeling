package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Process extends Element {

    private int queue, maxqueue, failure;
    private double meanQueue;

    public Process(double delay) {
        super(delay);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
    }

    @Override
    public void inAct() {
        if (state == 0) {
            state = 1;
            tnext = tcurr + super.getDelay();
        } else {
            if (queue < maxqueue) {
                queue++;
            } else {
                failure++;
            }
        }
    }

    @Override
    public void outAct() {
        super.outAct();
        tnext = Double.MAX_VALUE;
        state = 0;

        if (queue > 0) {
            queue--;
            state = 1;
            tnext = tcurr + super.getDelay();
        }
    }

    public void setMaxqueue(int maxqueue) {
        this.maxqueue = maxqueue;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + failure);
    }

    @Override
    public void doStatistics(double delta) {
        meanQueue = meanQueue + queue * delta;
    }

    public void printProcessResult(){
        System.out.println("mean length of queue = " +
                meanQueue / tcurr
                + "\nfailure probability  = " +
                failure / (double) quantity);
    }
}
