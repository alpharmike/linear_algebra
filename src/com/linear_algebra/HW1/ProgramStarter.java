package com.linear_algebra.HW1;

import com.linear_algebra.EquationSolver;
import com.linear_algebra.InputReader;
import com.linear_algebra.Matrix;
import com.linear_algebra.MatrixCalculator;

public class ProgramStarter {
    static InputReader inputReader;

    public ProgramStarter() {
        inputReader = new InputReader();
    }

    public static void run() {
        inputReader = new InputReader();
        System.out.println(inputReader);
        int dimension = inputReader.readDimension();
        Matrix A = inputReader.readMatrixInput(dimension);
        Matrix H = inputReader.readMatrixInput(dimension); // Hilbert Matrix
        Matrix b = inputReader.readRHSVector(dimension); // RHS Vector

        double detA = MatrixCalculator.getDeterminant(A);
        double detH = MatrixCalculator.getDeterminant(H);

        Matrix invA = MatrixCalculator.getInverseOfMatrix(A);
        Matrix invH = MatrixCalculator.getInverseOfMatrix(H);

        Matrix solutionA = EquationSolver.getSolution(A, b); // x for A
        Matrix solutionH = EquationSolver.getSolution(H, b); // x for H

        Matrix residualA = MatrixCalculator.subtractMatrices(b, MatrixCalculator.multiplyMatrices(A, solutionA));
        Matrix residualH = MatrixCalculator.subtractMatrices(b, MatrixCalculator.multiplyMatrices(A, solutionH));

        MatrixCalculator.calculateNorm(residualA);
        MatrixCalculator.calculateNorm(residualH);


    }
}
