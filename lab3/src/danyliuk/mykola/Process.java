package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Process extends Element {

    private int currentQueueLength, maxAllowedQueueLength, unservedQuantity;
    private double meanQueueLength;
    private int maxQueueLength; // максимальне значення черги

    public Process(String name, double delay) {
        super(name, delay);
        currentQueueLength = 0;
        maxAllowedQueueLength = Integer.MAX_VALUE;
        meanQueueLength = 0.0;
        maxQueueLength = 0;
    }

    @Override
    public void inAct() {
        if (state == 0) {
            state = 1;
            timeNext = tcurr + super.getDelay();
        } else {
            if (currentQueueLength < maxAllowedQueueLength) {
                currentQueueLength++;
                if(currentQueueLength > maxQueueLength){
                    maxQueueLength = currentQueueLength;
                }
            } else {
                unservedQuantity++;
            }
        }
    }

    @Override
    public void outAct() {
        super.outAct();
        timeNext = Double.MAX_VALUE;
        state = 0;

        if (currentQueueLength > 0) {
            currentQueueLength--;
            state = 1;
            timeNext = tcurr + super.getDelay();
        }
    }

    public void setMaxAllowedQueueLength(int maxAllowedQueueLength) {
        this.maxAllowedQueueLength = maxAllowedQueueLength;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + unservedQuantity + " queue = " + currentQueueLength);
    }

    @Override
    public void doStatistics(double delta) {
        meanQueueLength = meanQueueLength + currentQueueLength * delta;
    }

    public void printProcessResult(){
        System.out.println("Середнє спостережуване значення черги = " + getAverageLengthOfQueue());
        System.out.println("Ймовірність відмови в обслуговуванню = " + getFailureProbability());
        System.out.println("Максимальне спостережуване значення черги = " + maxQueueLength);
        System.out.println("Середній час очікування = " + getAverageWaitingTime());
    }

    private double getAverageLengthOfQueue(){
        return meanQueueLength / tcurr;
    }

    private double getFailureProbability() {
        return unservedQuantity / (double) quantity;
    }

    private double getAverageWaitingTime(){
        return meanQueueLength / (double) (quantity- unservedQuantity);
    }
}
