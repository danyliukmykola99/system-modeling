package danyliuk.mykola;

/**
 * @author Mykola Danyliuk
 */
public class Create extends Element {

    public Create(String name, double delay) {
        super(name, delay);
    }

    @Override
    public void outAct() {
        super.outAct();
        timeNext += super.getDelay();
        nextElement.inAct();
    }
}
