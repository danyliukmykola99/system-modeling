package danyliuk.mykola.ivanovich;

import java.util.List;

public class Simulator {

    private Data data;
    private List<Processor> processors;

    public Simulator(Data data, List<Processor> processors) {
        this.data = data;
        this.processors = processors;
    }

    private Double minTaskCompletionTime;
    private Processor minTaskCompletionTimeProcessor;

    public void start(){
        Processor initialProcessor = processors.get(0);

        while (true) {
            getMinTaskCompletionTimesProcessor();

            if(initialProcessor.getTaskCreationTime() > data.getTimeLimit() &&
                    minTaskCompletionTime > data.getTimeLimit()){
                break;
            }

            if(initialProcessor.getTaskCreationTime() < minTaskCompletionTime){
                initialProcessor.create();
            } else {
                minTaskCompletionTimeProcessor.complete();
                if(minTaskCompletionTimeProcessor.hasNextProcessors()){
                    Processor next = minTaskCompletionTimeProcessor.getNextProcessor();
                    if(next != null){
                        next.create();
                    } else {
                        minTaskCompletionTimeProcessor.dispose();
                    }
                } else {
                    minTaskCompletionTimeProcessor.dispose();
                }
            }

        }

        stats();
    }

    private void getMinTaskCompletionTimesProcessor(){
        minTaskCompletionTime = Double.MAX_VALUE;
        minTaskCompletionTimeProcessor = processors.get(0);
        for(Processor processor: processors){
            for(Double taskCompletion: processor.getTaskCompletionTimes()){
                if(taskCompletion < minTaskCompletionTime){
                    minTaskCompletionTimeProcessor = processor;
                    minTaskCompletionTime = taskCompletion;
                }
            }
        }
    }

    private void stats(){
        System.out.println("=================");
        processors.forEach(Processor::stats);
    }


}
