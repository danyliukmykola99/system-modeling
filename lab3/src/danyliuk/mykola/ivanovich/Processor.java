package danyliuk.mykola.ivanovich;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Processor {

    private String name;
    private HashMap<Processor, Double> nextProcessorsWithProbabilities;
    private Data data;
    private Integer queueLimit;
    private Integer numberOfCores;
    private Double avgTimeToCreate;
    private Double avgTimeToComplete;

    private Double taskCreationTime;
    private List<Double> taskCompletionTimes;

    public Processor(String name, Data data, Integer queueLimit, Integer numberOfCores, Double avgTimeToCreate,
                     Double avgTimeToComplete) {
        this.name = name;
        this.data = data;
        this.queueLimit = queueLimit;
        this.numberOfCores = numberOfCores;
        this.avgTimeToCreate = avgTimeToCreate;
        this.avgTimeToComplete = avgTimeToComplete;
    }

    public String getName() {
        return name;
    }

    public HashMap<Processor, Double> getNextProcessorsWithProbabilities() {
        return nextProcessorsWithProbabilities;
    }

    public Data getData() {
        return data;
    }

    public Integer getQueueLimit() {
        return queueLimit;
    }

    public Integer getNumberOfCores() {
        return numberOfCores;
    }

    public Double getAvgTimeToCreate() {
        return avgTimeToCreate;
    }

    public Double getAvgTimeToComplete() {
        return avgTimeToComplete;
    }

    public Double getTaskCreationTime() {
        return taskCreationTime;
    }

    public List<Double> getTaskCompletionTimes() {
        return taskCompletionTimes;
    }

    public Double getMinTaskCompletionTime(){
        return taskCompletionTimes.stream().min(Comparator.naturalOrder()).get();
    }

    public void create(){

    }
}
