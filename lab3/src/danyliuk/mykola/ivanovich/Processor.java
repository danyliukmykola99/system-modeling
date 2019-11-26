package danyliuk.mykola.ivanovich;

import danyliuk.mykola.FunRand;

import java.util.*;

public class Processor {

    private String name;
    private Map<Processor, Double> nextProcessorsWithProbabilities = new HashMap<>();
    private Data data;
    private int queueLimit;
    private int numberOfCores;
    private double avgTimeToCreate;
    private double avgTimeToComplete;
    private int queue;
    private double sumOfQueueLengths;
    private double timeOfQueueChanges;
    private double cpuLoadTime;
    private int maxCPULoad;

    private int created;
    private int completed;
    private int failed;

    private double taskCreationTime;

    public List<Double> getTaskCompletionTimes() {
        return taskCompletionTimes;
    }

    private List<Double> taskCompletionTimes;
    private int maxQueueLen;

    public Processor(String name, Data data, Integer queueLimit, Integer numberOfCores, Double avgTimeToCreate,
                     Double avgTimeToComplete) {
        this.name = name;
        this.data = data;
        this.queueLimit = queueLimit;
        this.numberOfCores = numberOfCores;
        this.avgTimeToCreate = avgTimeToCreate;
        this.avgTimeToComplete = avgTimeToComplete;
        this.taskCompletionTimes = new LinkedList<>();
    }

    public Double getTaskCreationTime() {
        return taskCreationTime;
    }

    public Double getMinTaskCompletionTime(){
        return taskCompletionTimes.stream().min(Comparator.naturalOrder()).orElse(null);
    }

    public void create(){
        data.setTime(taskCreationTime);
        taskCreationTime = data.getTime() + FunRand.Exp(avgTimeToCreate);
        created++;
        if(taskCompletionTimes.size() < numberOfCores){
            double timeToComplete = FunRand.Exp(avgTimeToComplete);
            taskCompletionTimes.add(data.getTime() + timeToComplete);
            cpuLoad(timeToComplete);
            event("create");
        } else if(queue < queueLimit){
            event("to queue");
        } else {
            event("failure");
        }
    }

    public void complete(){
        if(getMinTaskCompletionTime()!=null){
            data.setTime(getMinTaskCompletionTime());
        } else {
            data.setTime(data.getTime());
        }
        if(this.queue != 0){
            getTaskFromQueue();
        }
        taskCompletionTimes.remove(data.getTime());
        event("complete");
    }

    private void getTaskFromQueue() {
        queueLenChanges();
        this.queue--;
        double timeToComplete = FunRand.Exp(avgTimeToComplete);
        taskCompletionTimes.add(data.getTime() + timeToComplete);
        cpuLoad(timeToComplete);
    }

    private void queueLenChanges(){
        sumOfQueueLengths += (data.getTime() - timeOfQueueChanges) * queue;
        timeOfQueueChanges = data.getTime();
    }

    public void dispose(){
        System.out.println(name + " - disposed");
    }

    public void updateStats(String type){
        switch (type){
            case "create":{
                if(taskCompletionTimes.size() > maxCPULoad){
                    maxCPULoad = taskCompletionTimes.size();
                }
                break;
            }
            case "complete":{
                completed++;
                break;
            }
            case "to queue":{
                queueLenChanges();
                if(++queue > maxQueueLen){
                    maxQueueLen = queue;
                }
                break;
            }
            case "failure":{
                failed++;
                break;
            }
        }
    }

    public boolean hasNextProcessors(){
        return !nextProcessorsWithProbabilities.isEmpty();
    }

    public Processor getNextProcessor(){
        double randValue = Math.random();
        for(Map.Entry<Processor, Double> entry: nextProcessorsWithProbabilities.entrySet()){
            randValue -= entry.getValue();
            if(randValue <= 0){
                return entry.getKey();
            }
        }
        return null;
    }

    public void event(String type){
        updateStats(type);
        log(type);
    }

    public void log(String type){
        System.out.printf("Name: %s ", name);
        System.out.printf("Time: %10.3f ", data.getTime());
        System.out.printf("Busy cores: %d ", taskCompletionTimes.size());
        System.out.printf("Queue length: %d ", queue);
        System.out.printf("Type: %s%n", type);
    }

    public void stats(){
        if(avgCPULoad() > 0){
            System.out.printf("Name: %s ", name);
            System.out.printf("Created: %d ", created);
            System.out.printf("Completed: %d ", completed);
            System.out.printf("Failed: %d ", failed);
            System.out.printf("Failure probability: %10.3f ", calcFailureProbability());
            System.out.printf("Average queue length: %10.3f ", calAvgQueueLen());
            System.out.printf("Max queue length: %d ", maxQueueLen);
            System.out.printf("Average CPU load: %10.3f ", avgCPULoad());
            System.out.printf("Max CPU load: %d%n", maxCPULoad);
        }
    }

    public void cpuLoad(Double timeToComplete){
        if(data.getTime() + timeToComplete < data.getTimeLimit()){
            cpuLoadTime += timeToComplete;
        } else {
            cpuLoadTime += (data.getTimeLimit() - data.getTime());
        }
    }

    public Double avgCPULoad(){
        return cpuLoadTime / data.getTimeLimit() / numberOfCores * 100;
    }

    public Double calAvgQueueLen(){
        queueLenChanges();
        return sumOfQueueLengths / data.getTimeLimit();
    }

    public Double calcFailureProbability(){
        created = created == 0 ? 1 : created;
        return ((double) failed / (double) created) * 100;
    }

    public void setNextProcessors(List<Processor> processors, List<Double> probabilities){
        nextProcessorsWithProbabilities = new LinkedHashMap<>();
        if(processors.size() != probabilities.size()){
            throw new IllegalArgumentException();
        }
        int size = processors.size();
        for(int i = 0; i < size; i++){
            nextProcessorsWithProbabilities.put(processors.get(i), probabilities.get(i));
        }
    }
}
