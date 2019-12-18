package LibTest.utils;

import LibNet.NetLibrary;
import PetriObj.ExceptionInvalidNetStructure;
import PetriObj.ExceptionInvalidTimeDelay;
import PetriObj.PetriP;
import PetriObj.PetriSim;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static LibTest.utils.PetriNet.*;

public class PetriNetMatrices {

    public static Pair<ArrayList<PetriSim>, List<PetriP>> spawnNetsToMultiplyMatrices(int[][] mtr1, int[][] mtr2)
            throws ExceptionInvalidNetStructure, ExceptionInvalidTimeDelay {
        ArrayList<PetriSim> petriSims = new ArrayList<>();
        ArrayList<PetriP> resultsPetriP = new ArrayList<>();

        for (int i = 0; i < mtr1.length; i++) {
            for (int j = 0; j < mtr2[0].length; j++) {
                Pair<List<PetriSim>, PetriP> petriSimsAndResultPetriP =
                        createNetToCalcCell(i, j, mtr1, mtr2);
                petriSims.addAll(petriSimsAndResultPetriP.getKey());
                resultsPetriP.add(petriSimsAndResultPetriP.getValue());
            }
        }

        return new Pair<>(petriSims, resultsPetriP);
    }

    private static Pair<List<PetriSim>, PetriP> createNetToCalcCell(int row, int col, int[][] mtr1, int[][] mtr2)
            throws ExceptionInvalidTimeDelay, ExceptionInvalidNetStructure {
        ArrayList<PetriSim> nets = new ArrayList<>();

        for (int i = 0; i < mtr1[0].length; i++) {
            PetriSim net = new PetriSim(NetLibrary.CreateNetMultiplication(mtr1[row][i], mtr2[i][col]));
            nets.add(net);
        }

        if (nets.size() > 1) NetConnector.mergeNets(nets, "Result");

        PetriP resultPetriP = getPosition(getAllPositions(nets.get(0)), "Result");

        resultPetriP.setName(String.format("resultCell[%d][%d]", row, col));

        return new Pair<>(nets, resultPetriP);
    }

    /**
     * Print net positions in a form of a matrix
     * @param positions - List of net positions
     * @param numOfCols - Number of columns in the resulting matrix
     */
    public static void print(List<PetriP> positions, int numOfCols) {
        byte column = 0;
        for (PetriP petriP : positions) {
            if (column == numOfCols) {
                System.out.println();
                column = 0;
            }

            System.out.printf("%4d", petriP.getMark());

            column++;
        }
        System.out.println();
    }

}
