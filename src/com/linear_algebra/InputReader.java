package com.linear_algebra;

import java.util.Scanner;

public class InputReader {
    Scanner inputReader;

    public InputReader() {
        this.inputReader = new Scanner(System.in);
    }

    public int readDimension() {
        int dimension = this.inputReader.nextInt();
        return dimension;
    }

    public Matrix readMatrixInput(int dimension) {
        int rowIndex, colIndex;
        double elements[][] = new double[dimension][dimension];
        for (rowIndex = 0; rowIndex < dimension; ++rowIndex) {
            for (colIndex = 0; colIndex < dimension; ++colIndex) {
                elements[rowIndex][colIndex] = inputReader.nextDouble();
            }
        }

        return new Matrix(elements, dimension, dimension);
    }

    public Matrix readMatrixInput(int rowDimension, int colDimension) {
        int rowIndex, colIndex;
        double elements[][] = new double[rowDimension][colDimension];
        for (rowIndex = 0; rowIndex < rowDimension; ++rowIndex) {
            for (colIndex = 0; colIndex < colDimension; ++colIndex) {
                elements[rowIndex][colIndex] = inputReader.nextDouble();
            }
        }

        return new Matrix(elements, rowDimension, colDimension);
    }

    public Matrix readMatrixInput(int rowDimension, int colDimension, Scanner sc) {
        int rowIndex, colIndex;
        double elements[][] = new double[rowDimension][colDimension];
        for (rowIndex = 0; rowIndex < rowDimension; ++rowIndex) {
            for (colIndex = 0; colIndex < colDimension; ++colIndex) {
                elements[rowIndex][colIndex] = sc.nextDouble();
            }
        }

        return new Matrix(elements, rowDimension, colDimension);
    }

    public Matrix readRHSVector(int dimension) {
        int index;
        double elements[][] = new double[dimension][1];
        for (index = 0; index < dimension; ++index) {
            elements[index][0] = inputReader.nextDouble();
        }
//        inputReader.nextLine();
        return new Matrix(elements, dimension, 1);
    }

    public Matrix readRHSVector(int dimension, Scanner sc) {
        int index;
        double elements[][] = new double[dimension][1];
        for (index = 0; index < dimension; ++index) {
            elements[index][0] = sc.nextDouble();
        }
//        inputReader.nextLine();
        return new Matrix(elements, dimension, 1);
    }

    int readNumber() {
        return this.inputReader.nextInt();
    }

    public int[] readPermutationMatrix(int dimension) {
        int[] permutationMatrix = new int[dimension];
        int index;
        for (index = 0; index < dimension; ++index) {
            permutationMatrix[index] = inputReader.nextInt();
        }
        return permutationMatrix;
    }

}
