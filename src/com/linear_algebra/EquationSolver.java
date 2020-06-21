package com.linear_algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquationSolver {
    public static Matrix getSolution(Matrix coefficientMatrix, Matrix rightHandSide) { // the solution will be a vector
        Matrix inverseOfCoefficientMatrix = MatrixCalculator.getInverseOfMatrix(coefficientMatrix);
        if (inverseOfCoefficientMatrix == null) {
            return null;
        }
        Matrix solution = MatrixCalculator.multiplyMatrices(inverseOfCoefficientMatrix, rightHandSide);
        return solution;
    }

    public static Matrix solveByCramerRule(Matrix coefficientMatrix, Matrix rightHandSide) {
        double coefficientDeterminant = MatrixCalculator.getDeterminant(coefficientMatrix);
        if (coefficientDeterminant == 0) {
            return null;
        }

        double solutionVectorElements[][] = new double[coefficientMatrix.getNumOfColumns()][1];
        int solutionVectorIndex = 0;
        int colIndex;
        for (colIndex = 0; colIndex < coefficientMatrix.getNumOfColumns(); ++colIndex) {
            Matrix tempMatrix = MatrixCalculator.replaceColumn(coefficientMatrix, rightHandSide, colIndex + 1);
            double solutionElement = MatrixCalculator.getDeterminant(tempMatrix) / MatrixCalculator.getDeterminant(coefficientMatrix);
            solutionVectorElements[solutionVectorIndex][0] = solutionElement;
            ++solutionVectorIndex;
        }

        Matrix solutionVector = new Matrix(solutionVectorElements, coefficientMatrix.getNumOfColumns(), rightHandSide.getNumOfColumns());
        return solutionVector;
    }

    public static Matrix solveByQRFactorization(Matrix coefficientMatrix, Matrix rightHandSide) {
        Map<String, Object> QR = MatrixCalculator.applyQRFactorization(coefficientMatrix);


        Matrix Q = (Matrix) QR.get("Q");
        Matrix R = (Matrix) QR.get("R");

        Matrix QTranspose = MatrixCalculator.getTransposeMatrix(Q);
        Matrix newRightHandSide = MatrixCalculator.multiplyMatrices(QTranspose, rightHandSide);
        double[] solutions = MatrixCalculator.applyBackSubstitution(R, newRightHandSide); // applying back substitution to get the solutions
        List<Integer> dependentCols = (ArrayList<Integer>) QR.get("D");
        double[][] newSolElements = new double[coefficientMatrix.getNumOfColumns()][1];
        int index, enteredIndex;
        enteredIndex = 0;
        for (index = 0; index < coefficientMatrix.getNumOfColumns(); ++index) {
            if (dependentCols.contains(index)) {
                newSolElements[index][0] = 0;
            } else {
                newSolElements[index][0] = solutions[enteredIndex];
                ++enteredIndex;
            }
        }
        Matrix newSol = new Matrix(newSolElements, coefficientMatrix.getNumOfColumns(), 1);

        // the commented code is another way of calculating the solutions, but it's time expensive, for the fact that it needs the calculation of inverse of a matrix

        /* MatrixCalculator.getTransposeMatrix(Q).show();
        Matrix QRInverse = MatrixCalculator.multiplyMatrices(
                MatrixCalculator.getInverseOfMatrix(R),
                MatrixCalculator.getTransposeMatrix(Q)
        );
        Matrix sol = MatrixCalculator.multiplyMatrices(QRInverse, rightHandSide);
        double[][] newSolElements = new double[coefficientMatrix.getNumOfColumns()][1];
        int index, enteredIndex;
        enteredIndex = 0;
        for (index = 0; index < coefficientMatrix.getNumOfColumns(); ++index) {
            if (dependentCols.contains(index)) {
                newSolElements[index][0] = 0;
            } else {
                newSolElements[index][0] = sol.getElements()[enteredIndex][0];
                ++enteredIndex;
            }
        }
        Matrix newSol = new Matrix(newSolElements, coefficientMatrix.getNumOfColumns(), 1); */
        Matrix predictedY = MatrixCalculator.multiplyMatrices(coefficientMatrix, newSol);
//        System.out.println("sol");
//        newSol.show();
        if (MatrixCalculator.validSolution(rightHandSide, predictedY)) {
            return newSol;
        } else {
            return null;
        }

    }
}
