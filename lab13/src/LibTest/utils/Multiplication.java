package LibTest.utils;

import PetriObj.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static LibTest.utils.PetriNet.splitNets;
import static LibTest.utils.PetriNetMatrices.*;

public class Multiplication {

    public static double execute(Supplier<List<PetriP>> methodExecution, int iterations, int numOfCols) {
        long startTime;
        long endTime;
        double avgTime = 0;

        startTime = System.nanoTime();

        List<PetriP> result = null;
        for (int i = 0; i < iterations; ++i)
            result = methodExecution.get();

        endTime = System.nanoTime();
        avgTime = (endTime - startTime) / iterations;

        System.out.printf("Average time out of %d iterations: %f (ns)\n", iterations, avgTime);
        System.out.println("Resulting matrix:");
        print(result, numOfCols);

        return avgTime;
    }

    public static List<PetriP> multiplyConsistently(int[][] mtr1, int[][] mtr2) {
        Pair<ArrayList<PetriSim>, List<PetriP>> modelAndResultPetriPs;

        try {
            modelAndResultPetriPs = spawnNetsToMultiplyMatrices(mtr1, mtr2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        PetriObjModel model = new PetriObjModel(modelAndResultPetriPs.getKey());

        model.setIsProtokol(false);
        double timeModeling = Integer.MAX_VALUE;
        model.go(timeModeling);

        return modelAndResultPetriPs.getValue();
    }

    public static List<PetriP> multiplyParallel(int[][] mtr1, int[][] mtr2) {
        Pair<ArrayList<PetriSim>, List<PetriP>> netsWithPositions;

        try {
            netsWithPositions = spawnNetsToMultiplyMatrices(mtr1, mtr2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ArrayList<PetriSim> nets = netsWithPositions.getKey();
        PetriObjModel[] models = splitNets(mtr1[0].length * mtr2[0].length, nets);
        Thread[] threads = new Thread[models.length];

        for (int i = 0; i < threads.length; i++) {
            PetriObjModel model = models[i];
            threads[i] = new Thread(() -> {
                model.setIsProtokol(false);
                double timeModeling = Integer.MAX_VALUE;
                model.go(timeModeling);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return netsWithPositions.getValue();
    }

    public static List<PetriP> multiplyParallelByModel(int[][] mtr1, int[][] mtr2, int modelSize) {
        Pair<ArrayList<PetriSim>, List<PetriP>> netsWithPositions;

        try {
            netsWithPositions = spawnNetsToMultiplyMatrices(mtr1, mtr2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ArrayList<PetriSim> nets = netsWithPositions.getKey();
        PetriObjModel[] rowByColMultiplyingModels = splitNets(mtr1[0].length * mtr2[0].length, nets);
        ExecutorService executorService = Executors.newFixedThreadPool(modelSize);

        for (PetriObjModel curModel : rowByColMultiplyingModels) {
            executorService.submit(() -> {
                curModel.setIsProtokol(false);
                double timeModeling = 1000;
                curModel.go(timeModeling);
            });
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return netsWithPositions.getValue();
    }

}
