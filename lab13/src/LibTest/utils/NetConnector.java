package LibTest.utils;

import PetriObj.PetriP;
import PetriObj.PetriSim;
import java.util.ArrayList;

import static LibTest.utils.PetriNet.*;

public class NetConnector {

    private ArrayList<PetriP[]> positions = new ArrayList<>();

    public NetConnector(ArrayList<PetriSim> nets) {
        for (PetriSim net : nets)
            positions.add(net.getNet().getListP());
    }

    public static NetConnector mergeNets(ArrayList<PetriSim> nets, String jointPositionName) {
        return new NetConnector(nets).mergePositions(jointPositionName);
    }

    public NetConnector addPetriSim(PetriSim petriSim) {
        positions.add(petriSim.getNet().getListP());
        return this;
    }

    public NetConnector mergePositions(String jointPositionName) {
        if (positions.size() <= 1)
            throw new RuntimeException("Wrong number of merged petriSims (should be at least one)");

        PetriP mergePetriP = getPosition(positions.get(0), jointPositionName);

        for (PetriP[] petriSimHelper : positions)
            setPositionObject(petriSimHelper, jointPositionName, mergePetriP);

        return this;
    }

}
