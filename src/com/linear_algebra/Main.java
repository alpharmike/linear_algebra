package com.linear_algebra;

public class Main {

    public static void main(String[] args) {
	// write your code here
        double elements[][] = {{3, 0, 2}, {2, 2, 2}, {1, 1, 0}};
        double elements1[][] = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        double rhs[][] = {{1}, {2}, {3}};
        Matrix matrix = new Matrix(elements, 3, 3);
        Matrix matrix2 = new Matrix(elements1, 3, 3);
        Matrix rhsMatrix = new Matrix(rhs, 3, 1);
        Matrix inv = MatrixCalculator.getInverseOfMatrix(matrix);
//        inv.show();
//        MatrixCalculator.multiplyMatrices(inv, rhsMatrix).show();
        EquationSolver.getSolution(matrix, rhsMatrix);
//        MatrixCalculator.getInverseOfMatrix(matrix).show();
//        Matrix newMat = MatrixCalculator.eliminateMatrix(matrix, 1, 1);
//        System.out.println(newMat.getElements()[0][0]);

    }
}
