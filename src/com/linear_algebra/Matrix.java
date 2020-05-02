package com.linear_algebra;

public class Matrix {
    private double elements[][];
    private int numOfRows;
    private int numOfColumns;
    private boolean isSquare;

    public Matrix(double[][] elements, int numOfRows, int numOfColumns) {
        this.elements = elements;
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.isSquare = numOfRows == numOfColumns;
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

    public void show() {
        int rowIndex, colIndex;
        for (rowIndex = 0; rowIndex < this.numOfRows; ++rowIndex) {
            for (colIndex = 0; colIndex < this.numOfColumns; ++colIndex) {
                System.out.println(this.getElements()[rowIndex][colIndex] + " ");
            }
            System.out.println("\n");
        }
    }
}
