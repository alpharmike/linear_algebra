package com.linear_algebra;

import com.linear_algebra.EquationSolver;
import com.linear_algebra.InputReader;
import com.linear_algebra.Matrix;
import com.linear_algebra.MatrixCalculator;

public class ProgramRunner {
    static InputReader inputReader;

    public ProgramRunner() {
        inputReader = new InputReader();
    }

    public static void run() {
        inputReader = new InputReader();
        System.out.println(inputReader);
        int caseNumber = 1;
        double normResidualA = Double.POSITIVE_INFINITY;
        double normResidualH = Double.POSITIVE_INFINITY;

        while (true) {
            int dimension = inputReader.readDimension();
            System.out.println("Dimension of Input: " + dimension + " * " + dimension);
            if (dimension == 0) {
                System.out.println("end");
                break;
            }
            Matrix A = inputReader.readMatrixInput(dimension);
            Matrix H = inputReader.readMatrixInput(dimension); // Hilbert Matrix
            Matrix b = inputReader.readRHSVector(dimension); // RHS Vector
//        H.show();
            System.out.println("Case Number: " + caseNumber);
            double detA = MatrixCalculator.getDeterminant(A);
            double detH = MatrixCalculator.getDeterminant(H);
            System.out.println("Det A: " + detA);
            System.out.println("Det H: " + detH);

            // Note: Null is returned if inverse of the matrix does not exist(if det is zero)
            if (detA != 0) {
                Matrix invA = MatrixCalculator.getInverseOfMatrix(A);
                System.out.println("A Inverse:");
                invA.show();

                Matrix solutionA = EquationSolver.getSolution(A, b); // x for A
                System.out.println("Solution A:");
                solutionA.show();

                Matrix Ax = MatrixCalculator.multiplyMatrices(A, solutionA);
                Matrix residualA = MatrixCalculator.subtractMatrices(b, Ax);
                System.out.println("Residual A(b - Ax):");
                residualA.show();

                normResidualA = MatrixCalculator.calculateNorm(residualA);
                System.out.println("Norm of Residual A:" + normResidualA);
            }

            System.out.println("-------------------------------------");

            if (detH != 0) {
                Matrix invH = MatrixCalculator.getInverseOfMatrix(H);
                System.out.println("H Inverse:");
                invH.show();

                Matrix solutionH = EquationSolver.getSolution(H, b); // x for A
                System.out.println("Solution H:");
                solutionH.show();

                Matrix Hx = MatrixCalculator.multiplyMatrices(H, solutionH);
                Matrix residualH = MatrixCalculator.subtractMatrices(b, Hx);
                System.out.println("Residual H(b - Hx):");
                residualH.show();

                normResidualH = MatrixCalculator.calculateNorm(residualH);
                System.out.println("Norm of Residual H:" + normResidualH);
            }

            if (normResidualA != Double.POSITIVE_INFINITY && normResidualH != Double.POSITIVE_INFINITY) {
                if (normResidualA > normResidualH) {
                    System.out.println("||b - Ax|| > ||b - Hx||");
                } else if (normResidualA < normResidualH) {
                    System.out.println("||b - Ax|| < ||b - Hx||");
                } else {
                    System.out.println("||b - Ax|| = ||b - Hx||");
                }
            } else {
                System.out.println("Comparison is not possible because one of the equations could not be solved!");
            }

            System.out.println("-------------------------------------");
            ++caseNumber;
        }

    }
}
