package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Create extends Element {

    public Create(double delay) {
        super(delay);
    }

    @Override
    public void outAct() {
        super.outAct();
        tnext += super.getDelay();
        nextElement.inAct();
    }
}
