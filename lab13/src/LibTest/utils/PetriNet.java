package LibTest.utils;

import PetriObj.PetriObjModel;
import PetriObj.PetriP;
import PetriObj.PetriSim;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PetriNet {

    /**
     * Get all positions from the net
     * @param net - Petri network
     * @return List of positions
     */
    public static PetriP[] getAllPositions(PetriSim net) { return net.getNet().getListP(); }

    /**
     * Get position
     * @param positions - List of all positions in the net
     * @param name - Name of the desired position
     * @return Position's value
     */
    public static PetriP getPosition(PetriP[] positions, String name) {
        return Objects.requireNonNull(getPositionObject(positions, name)).getValue();
    }

    /**
     * Set a position's object
     * @param positions - List of all positions in the net
     * @param name - Name of the desired position
     * @param newPosition - Replacement position's object
     */
    public static void setPositionObject(PetriP[] positions, String name, PetriP newPosition) {
        Pair<Integer, PetriP> position = Objects.requireNonNull(getPositionObject(positions, name));
        positions[position.getKey()] = newPosition;
    }

    /**
     * Get position's object from the net along with its sequential number
     * @param positions - List of all positions in the net
     * @param name - Name of the desired position
     * @return Positions' sequential number and the position OR null
     */
    private static Pair<Integer, PetriP> getPositionObject(PetriP[] positions, String name) {
        for (int i = 0; i < positions.length; i++)
            if (positions[i].getName().equals(name))
                return new Pair<>(i, positions[i]);

        return null;
    }

    public static PetriObjModel[] splitNets(int modelsSize, List<PetriSim> petriSims) {
        PetriObjModel[] models = new PetriObjModel[modelsSize];
        int petriSimsPerRowByColMultiplying = petriSims.size() / modelsSize;

        int lastPetriSimsCount = petriSims.size() - (petriSims.size() / modelsSize) * modelsSize;
        int curIndex = petriSims.size() - lastPetriSimsCount;
        int endIndex = curIndex + lastPetriSimsCount;

        for (int i = 0; i < models.length; i++) {
            ArrayList<PetriSim> subPetriSims = new ArrayList<>(petriSims.subList(i * petriSimsPerRowByColMultiplying,
                    (i + 1) * petriSimsPerRowByColMultiplying));

            if (curIndex != endIndex) {
                subPetriSims.add(petriSims.get(curIndex));
                ++curIndex;
            }

            models[i] = new PetriObjModel(subPetriSims);
        }


        return models;
    }

}
