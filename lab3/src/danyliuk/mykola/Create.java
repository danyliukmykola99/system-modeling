package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Create extends Element {

    public Create(String name, double delay) {
        super(name, delay);
    }

    @Override
    public void updateTimeNext() {
        timeNext += super.getDelay();
    }

    @Override
    public void inAct() {}

    @Override
    public void printInfo() {
        System.out.printf("%s working = %s quantity = %d tnext = %3.3f%n", name, working, quantity, timeNext);
    }

    @Override
    public void doStatistics(double delta) {}

}
