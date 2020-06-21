package com.linear_algebra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
//        double elements[][] = {{3, 0, 2, 1, 0}, {2, 2, 2, 1, 5}, {1, 1, 0, 0, 6}, {2, 3, 1, 1, 2}, {4, 5, 2, 5, 6}};
//        double elements1[][] = {{3, 15, 2, 4}, {2, 2, 2, 5}, {1, 5, 6, 2}, {7, 2, 6, 1}};
//        double rhs[][] = {{1}, {2}, {3}};
//        Matrix matrix = new Matrix(elements, 5, 5);
//        System.out.println(MatrixCalculator.getDeterminant(matrix));
//        Matrix matrix2 = new Matrix(elements1, 3, 3);
//        Matrix rhsMatrix = new Matrix(rhs, 3, 1);
//        Matrix inv = MatrixCalculator.getInverseOfMatrix(matrix);
//        inv.show();
//        MatrixCalculator.multiplyMatrices(inv, rhsMatrix).show();
//        matrix.show();
//        EquationSolver.solveByCramerRule(matrix, rhsMatrix).show();
//        MatrixCalculator.getInverseOfMatrix(matrix).show();
//        Matrix newMat = MatrixCalculator.eliminateMatrix(matrix, 1, 1);
//        System.out.println(newMat.getElements()[0][0]);

//        InputReader inputReader = new InputReader();
//        int a = inputReader.readDimension();
//      inputReader.readMatrixInput(3);
//        ProgramRunner.run();

//        ProgramRunner2.run();
//
//        double elements[][] = {{5, 2, 7}, {3, 7, 10}, {11, 8, 19}};
//        Matrix matrix = new Matrix(elements, 3, 3);
//        double rhs[][] = {{0}, {1}, {2}};
//        Matrix rhsMatrix = new Matrix(rhs, 3, 1);
//        Map<String, Matrix> QR = MatrixCalculator.applyQRFactorization(matrix);
//        Matrix sol = EquationSolver.solveByQRFactorization(matrix, rhsMatrix);
//        QR.get("R").show();
//        QR.get("Q").show();
//        sol.show();

//        double elements[][] = {{1, 2, -4}, {3, 4, -6}, {5, 6, -8}};
//        Matrix matrix = new Matrix(elements, 3, 3);
//////        double rhs[][] = {{0}, {1}, {2}};
//////        Matrix rhsMatrix = new Matrix(rhs, 3, 1);
//        double sol[][] = {{2.48030}, {-1.07044}, {0.109852}};
//        Matrix solMatrix = new Matrix(sol, 3, 1);
//        MatrixCalculator.multiplyMatrices(matrix, solMatrix).show();

        ProgramRunner3.run();

    }
}
