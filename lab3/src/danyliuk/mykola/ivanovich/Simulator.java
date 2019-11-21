package danyliuk.mykola.ivanovich;

import java.util.Comparator;
import java.util.List;

public class Simulator {

    private Data data;
    private List<Processor> processors;

    public Simulator(Data data, List<Processor> processors) {
        this.data = data;
        this.processors = processors;
    }

    public void start(){
        Processor initialProcessor = processors.get(0);

        while (true) {
            Processor minTaskCompletionTimesProcessor = getMinTaskCompletionTimesProcessor();
            Double minCompletionTimeValue = minTaskCompletionTimesProcessor.getMinTaskCompletionTime();

            if(initialProcessor.getTaskCreationTime() > data.getTimeLimit() &&
                    minCompletionTimeValue > data.getTimeLimit()){
                break;
            }

            if(initialProcessor.getTaskCreationTime() < minCompletionTimeValue){
                initialProcessor.create();
            } else {
                minTaskCompletionTimesProcessor.complete();
                if(minTaskCompletionTimesProcessor.hasNextProcessors()){
                    Processor next = minTaskCompletionTimesProcessor.getNextProcessor();
                    if(next != null){
                        next.create();
                    } else {
                        minTaskCompletionTimesProcessor.dispose();
                    }
                } else {
                    minTaskCompletionTimesProcessor.dispose();
                }
            }

        }

        stats();
    }

    private Processor getMinTaskCompletionTimesProcessor(){
        Double minValue = Double.MAX_VALUE;
        Processor processorWithMinValue = processors.get(0);
        for(Processor processor: processors){
            for(Double taskCompletion: processor.getTaskCompletionTimes()){
                if(taskCompletion < minValue){
                    processorWithMinValue = processor;
                    minValue = taskCompletion;
                }
            }
        }
        return processorWithMinValue;
    }

    private void stats(){
        System.out.println("=================");
        processors.forEach(Processor::stats);
    }


}
