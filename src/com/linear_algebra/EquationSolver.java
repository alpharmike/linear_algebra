package com.linear_algebra;

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
}
