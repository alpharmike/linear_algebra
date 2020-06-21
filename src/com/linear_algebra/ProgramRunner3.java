package com.linear_algebra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgramRunner3 {
    static InputReader inputReader;

    public ProgramRunner3() {
        inputReader = new InputReader();
    }

    public static void run() throws FileNotFoundException {
        File file = new File("in.txt");
        Scanner sc = new Scanner(file);

        inputReader = new InputReader();

        while (sc.hasNextLine()) {
            int caseNumber = sc.nextInt();
            int caseCounter;
            for (caseCounter = 0; caseCounter < caseNumber; ++caseCounter) {
                System.out.println("Case Number: " + (caseCounter + 1));
                int rowDimension = sc.nextInt();
                int colDimension = sc.nextInt();
                Matrix matrix = inputReader.readMatrixInput(rowDimension, colDimension, sc);
                Matrix RHSVector = inputReader.readRHSVector(rowDimension, sc);

                Matrix solutionMatrix = EquationSolver.solveByQRFactorization(matrix, RHSVector);
                System.out.println("Solution: ");
                if (solutionMatrix != null) {
                    solutionMatrix.show();
                } else {
                    System.out.println("No Solution");
                }
                System.out.printf("\n");
            }
            while (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
//        inputReader = new InputReader();
//        int caseNumber = inputReader.readNumber();
//        int caseCounter;
//        for (caseCounter = 0; caseCounter < caseNumber; ++caseCounter) {
//            System.out.println("case number: " + caseCounter);
//            int rowDimension = inputReader.readDimension();
//            int colDimension = inputReader.readDimension();
//            System.out.println(rowDimension);
//            System.out.println(colDimension);
//            Matrix matrix = inputReader.readMatrixInput(rowDimension, colDimension);
//            Matrix RHSVector = inputReader.readRHSVector(rowDimension);
//
//            Matrix solutionMatrix = EquationSolver.solveByQRFactorization(matrix, RHSVector);
//            System.out.println("Solution: ");
//            if (solutionMatrix != null) {
//                solutionMatrix.show();
//            } else {
//                System.out.println("No Solution");
//            }
//        }
    }
}
