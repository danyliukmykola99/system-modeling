package danyliuk.mykola.model;

/**
 * @author Mykola Danyliuk
 */
public class ExponentialDistribution implements Distribution {

    private double mean;

    public ExponentialDistribution(double mean) {
        this.mean = mean;
    }

    @Override
    public double value() {
        double a = 0;
        while (a == 0) {
            a = Math.random();
        }
        a = -mean * Math.log(a);

        return a;
    }
}
