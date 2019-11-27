package danyliuk.mykola.model;

/**
 * @author Mykola Danyliuk
 */
public class Queue {

    private Model model;
    private int maxQuantity;
    private int currentQuantity;
    private Distribution processingDistribution;
    private Double processingStartTime;
    private Double processingEndTime;
    private boolean processing;

    private double quantityLastChangeTime;
    private double quantityChangeSum;

    private double lastProcessedTime;
    private double betweenProcessedTimeSum;

    public Queue(Model model, int maxQuantity, Distribution processingDistribution) {
        this.model = model;
        this.maxQuantity = maxQuantity;
        this.processingDistribution = processingDistribution;
        this.processing = false;
        this.currentQuantity = 0;
        this.quantityChangeSum = this.quantityLastChangeTime = 0.0;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void add(){
        handleQuantityChange();
        currentQuantity++;
        if(currentQuantity == 1 && !processing){
            changeProcessingElement();
        }
    }

    public void finish(){
        processing = false;
        model.addProcessed(processingEndTime - processingStartTime);
        changeProcessingElement();
        statisticOnProcessingEnd();
    }

    private void statisticOnProcessingEnd(){
        double currentTime = getCurrentTime();
        betweenProcessedTimeSum += (currentTime - lastProcessedTime);
        lastProcessedTime = currentTime;
    }

    private void handleQuantityChange(){
        double currentTime = getCurrentTime();
        quantityChangeSum += currentQuantity * (currentTime - quantityLastChangeTime);
        quantityLastChangeTime = currentTime;
        model.addQuantityChange();
    }

    private void changeProcessingElement(){
        if(currentQuantity != 0){
            processing = true;
            handleQuantityChange();
            currentQuantity--;
            processingStartTime = getCurrentTime();
            processingEndTime = processingStartTime + processingDistribution.value();
        }
    }

    public boolean isNotFull(){
        return maxQuantity > currentQuantity;
    }

    public boolean isProcessing() {
        return processing;
    }

    public Double getProcessingEndTime() {
        return processingEndTime;
    }

    public void printStatus(){
        System.out.printf("currentQuantity: %d Processing: %s Processing End Time: %3.3f%n",
                currentQuantity, processing, processingEndTime);
    }

    public void printAverageQuantity(){
        System.out.printf("Average quantity in queue: %2.3f%n", quantityChangeSum / getCurrentTime());
    }

    public void printAverageTimeBetweenProcessingEnd(){
        System.out.printf("Average time between processing end: %2.3f%n", betweenProcessedTimeSum / getCurrentTime());
    }

    public Double getCurrentTime(){
        return model.getCurrentTime();
    }
}
