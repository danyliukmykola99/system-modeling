package danyliuk.mykola.impl;

import danyliuk.mykola.model.ExponentialDistribution;
import danyliuk.mykola.model.Model;

/**
 * @author Mykola Danyliuk
 */
public class BankModel {

    private Model model;

    public BankModel(){
        model = new Model(new ExponentialDistribution(0.3));
        model.addQueue(3, new ExponentialDistribution(0.5));
        model.addQueue(3, new ExponentialDistribution(0.5));
    }

    public void run(Double simulationTime){
        model.run(simulationTime);
    }

}