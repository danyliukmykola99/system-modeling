package danyliuk.mykola;

import danyliuk.mykola.model.SimulationModel;

/**
 * @author Mykola Danyliuk
 */
public class SimulationModelApplication {

    public static void main(String[] args) {

        SimulationModel model = new SimulationModel(2.0,1.0, 0.5, 0.2, 0.8, 1.0, 0.5);
        model.run();

    }
}