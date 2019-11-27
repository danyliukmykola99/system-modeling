package danyliuk.mykola.model;

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
        maxAllowedQueueLength = 3;
        meanQueueLength = 0.0;
        maxQueueLength = 0;
    }

    @Override
    public void inAct() {
        System.out.printf("%3.3f IN ACT %s%n",currentTime , name);
        if (!working) {
            working = true;
            timeNext = currentTime + super.getDelay();
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
    public void updateTimeNext() {
        timeNext = Double.MAX_VALUE;
        working = false;
        if (currentQueueLength > 0) {
            currentQueueLength--;
            working = true;
            timeNext = currentTime + super.getDelay();
        }
    }

    @Override
    public void printInfo() {
        System.out.printf("%s working = %s quantity = %d tnext = %3.3f failure = %d queue = %d%n",
                name, working, quantity, timeNext, unservedQuantity, currentQueueLength);
    }

    @Override
    public void doStatistics(double delta) {
        meanQueueLength = meanQueueLength + currentQueueLength * delta;
    }

    public void printResult(){
        super.printResult();
        System.out.println("Середнє спостережуване значення черги = " + getAverageLengthOfQueue());
        System.out.println("Ймовірність відмови в обслуговуванню = " + getFailureProbability());
        System.out.println("Максимальне спостережуване значення черги = " + maxQueueLength);
        System.out.println("Середній час очікування = " + getAverageWaitingTime());
    }

    private double getAverageLengthOfQueue(){
        return meanQueueLength / currentTime;
    }

    private double getFailureProbability() {
        return unservedQuantity / (double) quantity;
    }

    private double getAverageWaitingTime(){
        return meanQueueLength / (double) (quantity- unservedQuantity);
    }

}