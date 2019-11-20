package danyliuk.mykola;

import java.util.LinkedList;
import java.util.Queue;

public class Model1 {

    private double timeAvgToCreate;
    private double timeAvgToComplete;
    private int maxQueueLen;
    private double initialTime;
    private double time;
    private boolean isBusy;
    private double timeOfCreation;
    private double timeOfCompletion;
    private int queueLen;
    private int numCompleted;
    private int numCreated;
    private int numFailed;
    private int sumOfRangesLoad;
    private double sumOfWaitInQueueTime;
    private int numsWereInQueue;
    private Queue<Double> timeOfPutInQueue;
    private double maxExecTime;
    private EventType eventType;

    public Model1(double timeAvgToCreate, double timeAvgToComplete, int maxQueueLen, double currentTime, boolean isBusy,
                  double appearTime, double completeTime) {
        this.timeAvgToCreate = timeAvgToCreate;
        this.timeAvgToComplete = timeAvgToComplete;
        this.maxQueueLen = maxQueueLen;
        this.initialTime = this.time = currentTime;
        this.isBusy = isBusy;
        this.timeOfCreation = currentTime + appearTime;
        this.timeOfCompletion = currentTime + completeTime;
        this.timeOfPutInQueue = new LinkedList<>();
        eventType = EventType.create;
    }

    void simulate(double maxExecTime){
        this.maxExecTime = maxExecTime;

        while(time < maxExecTime){
            if(timeOfCompletion < timeOfCreation){
                eventComplete();
            } else {
                eventCreate();
            }
            printStepInfo();
        }
        printOverallInfo();
    }

    private void eventCreate(){
        time = timeOfCreation;
        eventType = EventType.create;

        // If we try to create after ending of execution time - don't count it
        if(timeOfCreation <= maxExecTime){
            numCreated++;
        }

        // If initial state is busy - don't count completion of it
        if(isBusy){
            numCreated--;
        }

        // Start to process or put in the queue
        if(!isBusy){
            isBusy = true;
            double timeToCreate = getTimeToCreate();
            timeOfCreation = time + timeToCreate;
            sumUpRangesLoad(timeToCreate);
        } else {
            if(queueLen < maxQueueLen){
                queueLen++;
                // remember time when push in the queue
                timeOfPutInQueue.add(time);
            } else {
                numFailed++;
            }
        }


    }

    private void eventComplete(){
        time = timeOfCompletion;
        eventType = EventType.completed;

        timeOfCompletion = Double.POSITIVE_INFINITY;
        numCompleted++;
        isBusy = false;

        // Get an element from the queue if it's not empty
        if(queueLen > 0){
            queueLen--;
            sumOfWaitInQueueTime += (time - timeOfPutInQueue.poll());
            numsWereInQueue++;

            isBusy = true;
            double timeToComplete = getTimeToComplete();
            timeOfCompletion = time + timeToComplete;
            sumUpRangesLoad(timeToComplete);
        }
    }

    private double getTimeToCreate(){
        return FunRand.Exp(timeAvgToCreate);
    }

    private double getTimeToComplete(){
        return FunRand.Exp(timeAvgToComplete);
    }

    private void sumUpRangesLoad(double timeToComplete){
        if(time < maxExecTime){
            if(timeOfCompletion <= maxExecTime){
                sumOfRangesLoad += timeToComplete;
            } else {
                sumOfRangesLoad += (maxExecTime - time);
            }
        }
    }

    private double getAvgDeviceLoad(){
        return sumOfRangesLoad / (maxExecTime - initialTime);
    }

    private double getAvgWaitTime(){
        while (!timeOfPutInQueue.isEmpty()){
            double taskTime = timeOfPutInQueue.poll();
            if (taskTime <= maxExecTime){
                numsWereInQueue++;
                sumOfWaitInQueueTime += maxExecTime - taskTime;
            }
        }

        if(numsWereInQueue == 0){
            return 0;
        }

        return sumOfWaitInQueueTime / numsWereInQueue;
    }

    private void printStepInfo(){
        System.out.printf("Time: %10.3f, Is busy: %b, Queue length: %d, Type: %s%n",
                time, isBusy, queueLen, eventType.name());
    }

    private void printOverallInfo(){
        System.out.printf("#Created: %d, #Completed: %d, #Failed: %d, Avg load: %10.3f, Avg waiting time: %10.3f%n",
                numCreated, numCompleted, numFailed, getAvgDeviceLoad(), getAvgWaitTime());
    }

    enum EventType{
        create,
        completed
    }
}


