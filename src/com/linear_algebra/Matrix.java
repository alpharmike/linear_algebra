package com.linear_algebra;

public class Matrix { // Note that a vector has been considered as a matrix, with the specification that the number of columns must be 1, and the number of rows must be any integer n
    private double elements[][];
    private int numOfRows;
    private int numOfColumns;
    private boolean isSquare;
    private boolean isVector;

    public Matrix(double[][] elements, int numOfRows, int numOfColumns) {
        this.elements = elements;
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.isSquare = numOfRows == numOfColumns;
        this.isVector = numOfColumns == 1 && numOfRows >= 1;
    }

    public double[][] getElements() {
        return elements;
    }

    public void setElements(double[][] elements) {
        this.elements = elements;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public void setNumOfColumns(int numOfColumns) {
        this.numOfColumns = numOfColumns;
    }

    public boolean isSquare() {
        return isSquare;
    }

    public void setSquare(boolean square) {
        isSquare = square;
    }

    public boolean isVector() {
        return isVector;
    }

    public void setVector(boolean vector) {
        isVector = vector;
    }

    public void show() {
        int rowIndex, colIndex;
        for (rowIndex = 0; rowIndex < this.numOfRows; ++rowIndex) {
            for (colIndex = 0; colIndex < this.numOfColumns; ++colIndex) {
                System.out.printf("%g ", this.getElements()[rowIndex][colIndex]);
            }
            System.out.printf("\n");
        }
    }
}
