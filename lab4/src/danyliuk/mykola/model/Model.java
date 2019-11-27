package danyliuk.mykola.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Mykola Danyliuk
 */
public class Model {

    private List<Queue> queues;
    private Double currentTime;
    private Distribution receiptDistribution;
    private Double nextReceiptTime;

    private int receiptQuantity;
    private int failedReceiptQuantity;

    private int processedQuantity;
    private double processedTimeSum;

    private double quantityLastChangeTime;
    private double quantityChangeSum;

    public Model(Distribution receiptDistribution) {
        this.queues = new ArrayList<>();
        this.currentTime = this.nextReceiptTime = this.processedTimeSum = 0.0;
        this.receiptQuantity = this.failedReceiptQuantity = this.processedQuantity = 0;
        this.receiptDistribution = receiptDistribution;
    }

    public void addQueue(int maxQuantity, Distribution processingDistribution){
        queues.add(new Queue(this, maxQuantity, processingDistribution));
    }

    public void run(Double simulationTime){
        while (currentTime < simulationTime){
            Optional<Queue> nextEndProcessingQueue = getQueueWithMinProcessingEndTime();
            double nextProcessingEndTime = Double.MAX_VALUE;
            if(nextEndProcessingQueue.isPresent()){
                nextProcessingEndTime = nextEndProcessingQueue.get().getProcessingEndTime();
            }
            if(nextProcessingEndTime <= nextReceiptTime){
                Queue q = nextEndProcessingQueue.get();
                System.out.println("-----  END PROCESSING  ------");
                currentTime = nextProcessingEndTime;
                q.finish();
            } else {
                System.out.println("--------  RECEIPT  ----------");
                currentTime = nextReceiptTime;
                handleReceipt();
            }
            printCurrentStatus();
        }
        printResult();
    }

    private void handleReceipt(){
        receiptQuantity++;
        nextReceiptTime += receiptDistribution.value();
        Optional<Queue> queueToAdd = getNotFullQueueWithMinCurrentQuantity();
        if(queueToAdd.isPresent()){
            queueToAdd.get().add();
        } else {
            failedReceiptQuantity++;
        }
    }

    public void addProcessed(double processedTime){
        processedTimeSum += processedTime;
        processedQuantity++;
    }

    public void addQuantityChange(){
        int quantity = queues.stream().mapToInt(Queue::getCurrentQuantity).sum();
        quantityChangeSum += quantity * (currentTime - quantityLastChangeTime);
        quantityLastChangeTime = currentTime;
    }

    public Optional<Queue> getNotFullQueueWithMinCurrentQuantity(){
        return queues.stream().filter(Queue::isNotFull).min(Comparator.comparing(Queue::getCurrentQuantity));
    }

    public Optional<Queue> getQueueWithMinProcessingEndTime(){
        return queues.stream().filter(Queue::isProcessing).min(Comparator.comparing(Queue::getProcessingEndTime));
    }

    public Double getCurrentTime() {
        return currentTime;
    }

    public void printCurrentStatus(){
        System.out.printf("Current Time: %3.3f%n", currentTime);
        queues.forEach(Queue::printStatus);
    }

    public void printResult(){
        System.out.println("------  RESULT  ------");
        System.out.printf("Failure probability: %2.3f%%%n", (double) failedReceiptQuantity / receiptQuantity * 100);
        System.out.printf("Average processing time: %2.3f%n", processedTimeSum / processedQuantity);
        queues.forEach(Queue::printAverageQuantity);
        System.out.printf("Average quantity in model: %2.3f%n", quantityChangeSum / currentTime);
        queues.forEach(Queue::printAverageTimeBetweenProcessingEnd);
    }

}
