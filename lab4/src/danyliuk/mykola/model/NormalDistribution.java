package danyliuk.mykola.model;

import java.util.Random;

/**
 * @author Mykola Danyliuk
 */
public class NormalDistribution implements Distribution {

    private double mean;
    private double deviation;

    public NormalDistribution(double mean, double deviation) {
        this.mean = mean;
        this.deviation = deviation;
    }

    @Override
    public double value(){
        double a;
        Random r = new Random();
        a = mean + deviation * r.nextGaussian();
        return a;
    }

}
