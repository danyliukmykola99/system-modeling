package danyliuk.mykola.model;

/**
 * @author Mykola Danyliuk
 */
public class UniformDistribution implements Distribution {

    private double min;
    private double max;

    public UniformDistribution(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double value() {
        double a = 0;
        while (a == 0) {
            a = Math.random();
        }
        a = min + a * (max - min);
        return a;
    }
}
