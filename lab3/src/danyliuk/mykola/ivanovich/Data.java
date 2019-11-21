package danyliuk.mykola.ivanovich;

public class Data {

    private double time;
    private double timeLimit;

    public Data(double time, double timeLimit) {
        this.time = time;
        this.timeLimit = timeLimit;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public double getTime() {
        return time;
    }

    public double getTimeLimit() {
        return timeLimit;
    }
}
