package danyliuk.mykola;

import danyliuk.mykola.impl.BankModel;

/**
 * @author Mykola Danyliuk
 */
public class BankApplication {

    public static void main(String[] args){
        BankModel model = new BankModel();
        model.run(10.0);
    }

}
