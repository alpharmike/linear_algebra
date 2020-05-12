package com.linear_algebra;

import java.util.Scanner;

public class InputReader {
    Scanner inputReader;

    public InputReader() {
        this.inputReader = new Scanner(System.in);
    }

    public int readDimension() {
//        this.inputReader.nextLine();
        int dimension = this.inputReader.nextInt();
        return dimension;
    }

    public Matrix readMatrixInput(int dimension) {
        int rowIndex, colIndex;
//        this.inputReader.nextLine();
        double elements[][] = new double[dimension][dimension];
        for (rowIndex = 0; rowIndex < dimension; ++rowIndex) {
            for (colIndex = 0; colIndex < dimension; ++colIndex) {
                elements[rowIndex][colIndex] = inputReader.nextDouble();
            }
        }
        return new Matrix(elements, dimension, dimension);
    }

    public Matrix readRHSVector(int dimension) {
        int index;
        double elements[][] = new double[dimension][1];
        for (index = 0; index < dimension; ++index) {
            elements[index][0] = inputReader.nextDouble();
        }

        return new Matrix(elements, dimension, 1);
    }

}
