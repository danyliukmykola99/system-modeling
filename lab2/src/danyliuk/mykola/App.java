package danyliuk.mykola;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Mykola Danyliuk
 */
public class App {

    private static Bus bus;
    private static Random random;
    private static int randomParameter;
    private static final int MAXIMUM_PASSENGERS = 50;

    private static int stopCount;
    private static int passengersCount;
    private static double timeCount;
    private static double passengersInTimeCount;

    public static void main(String[] args){
        System.out.print("Input random parameter: ");
        Scanner scanner = new Scanner(System.in);
        randomParameter = scanner.nextInt();
        bus = new Bus();
        random = new Random();
        stopCount = 0;
        passengersCount = 0;
        for(int i = 0; i < 10; i++){
            run();
        }
    }

    private static void run(){
        stopCount++;
        int millisecondsToTheNextStop = random.nextInt(2000);
        timeCount += (double) millisecondsToTheNextStop / 1000;
        try {
            System.out.println("RIDING ... " + millisecondsToTheNextStop + " milliseconds");
            Thread.sleep(millisecondsToTheNextStop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int passengersOld = bus.getPassengers();
        int passengersOut = Math.min(random.nextInt(randomParameter), passengersOld);
        int passengersIn = Math.min(random.nextInt(randomParameter), MAXIMUM_PASSENGERS - passengersOld);
        int passengers = passengersOld + passengersIn - passengersOut;
        passengersCount += passengers;
        passengersInTimeCount += passengers*( (double) millisecondsToTheNextStop / 1000 );
        double averagePassengers = (double) passengersCount / (double) stopCount;
        double averagePassengersInTimeCount = passengersInTimeCount / timeCount;
        bus.setPassengers(passengers);
        System.out.println("IN: " + passengersIn + " OUT: " + passengersOut + " COUNT: " + passengers + " AVERAGE: " + averagePassengers + " AVERAGE PER SECOND " + averagePassengersInTimeCount);
    }
}
