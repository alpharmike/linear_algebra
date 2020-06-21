package com.linear_algebra;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramRunner2 {
    static InputReader inputReader;

    public ProgramRunner2() {
        inputReader = new InputReader();
    }

    public static void run() {
        inputReader = new InputReader();
        int caseNumber = inputReader.readNumber();
        int caseCounter;
        for (caseCounter = 0; caseCounter < caseNumber; ++caseCounter) {
            int dimension = inputReader.readDimension();
            int[] permutationMatrix = inputReader.readPermutationMatrix(dimension);
            int[] squareRootMatrix = Permutation.permutationSqrt(permutationMatrix);
            System.out.println("Case Number: " + caseCounter + 1);
            if (squareRootMatrix != null) {
                Permutation.showPermutation(squareRootMatrix);
            } else {
                System.out.println("Impossible");
            }
        }
    }
}
