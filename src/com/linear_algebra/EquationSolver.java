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
}
