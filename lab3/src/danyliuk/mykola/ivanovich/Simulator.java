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
                    minTaskCompletionTimesProcessor.getNextProcessor().create();
                } else {
                    minTaskCompletionTimesProcessor.dispose();
                }
            }

        }

        stats();
    }

    private Processor getMinTaskCompletionTimesProcessor(){
        return processors.stream().min(Comparator.comparing(Processor::getMinTaskCompletionTime)).get();
    }

    private void stats(){
        System.out.println("=================");
        processors.forEach(Processor::stats);
    }


}
