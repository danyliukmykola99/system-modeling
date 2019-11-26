package danyliuk.mykola;

import danyliuk.mykola.impl.MessengerModel;

/**
 * @author Mykola Danyliuk
 */
public class MessengerApplication {

    public static void main(String[] args){
        MessengerModel model = new MessengerModel();
        model.run();
    }

}
