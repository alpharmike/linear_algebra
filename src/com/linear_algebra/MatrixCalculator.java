package com.linear_algebra;

public class MatrixCalculator {
    public static double getDeterminant(Matrix matrix) {
        if (!matrix.isSquare()) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return calcDeterminantRec(matrix, 0);
        }
    }

    private static double calcDeterminantRec(Matrix matrix, double initialValue) {
        if (matrix.getNumOfRows() == 1 && matrix.getNumOfColumns() == 1) {
            return matrix.getElements()[0][0];
        }
        if (matrix.getNumOfRows() == 2 && matrix.getNumOfColumns() == 2) {
            return matrix.getElements()[0][0] * matrix.getElements()[1][1] - matrix.getElements()[0][1] * matrix.getElements()[1][0];
        }

        int i, j;
        for (i = 0; i < matrix.getNumOfColumns(); ++i) {
            Matrix modMat = eliminateMatrix(matrix, 1, i + 1);
            initialValue += Math.pow(-1, i) * matrix.getElements()[0][i] * calcDeterminantRec(modMat, initialValue);

        }
        return initialValue;
    }


    public static Matrix eliminateMatrix(Matrix matrix, int rowNumber, int colNumber) {
//        double modifiedMatrix = new Matrix();
        double matrixElements[][] = new double[matrix.getNumOfRows() - 1][matrix.getNumOfColumns() - 1];
        int rowIndex, colIndex, currentRow, currentCol;
        currentRow = 0;
        for (rowIndex = 0; rowIndex < matrix.getNumOfRows(); ++rowIndex) {
            if (rowIndex + 1 != rowNumber) {
                currentCol = 0;
                for (colIndex = 0; colIndex < matrix.getNumOfColumns(); ++colIndex) {
                    if (colIndex + 1 != colNumber) {
                        matrixElements[currentRow][currentCol] = matrix.getElements()[rowIndex][colIndex];
                        ++currentCol;
                    }
                }
                ++currentRow;
            }
        }
        Matrix modifiedMatrix = new Matrix(matrixElements, matrix.getNumOfRows() - 1, matrix.getNumOfColumns() - 1);
        return modifiedMatrix;
    }

    public static Matrix getCoFactorMatrix(Matrix matrix) {
        int rowIndex, colIndex;
        double coFactorElements[][] = new double[matrix.getNumOfRows()][matrix.getNumOfColumns()];
        for (rowIndex = 0; rowIndex < matrix.getNumOfRows(); ++rowIndex) {
            for (colIndex = 0; colIndex < matrix.getNumOfColumns(); ++colIndex) {
                coFactorElements[rowIndex][colIndex] = Math.pow(-1, rowIndex + colIndex) * calcDeterminantRec(eliminateMatrix(matrix, rowIndex + 1, colIndex + 1), 0);
            }
        }
        Matrix coFactorMatrix = new Matrix(coFactorElements, matrix.getNumOfRows(), matrix.getNumOfColumns());
        return coFactorMatrix;
    }

    public static Matrix getTransposeMatrix(Matrix matrix) {
        double transposeElements[][] = new double[matrix.getNumOfRows()][matrix.getNumOfColumns()];
        int rowIndex, colIndex;
        for (rowIndex = 0; rowIndex < matrix.getNumOfRows(); ++rowIndex) {
            for (colIndex = 0; colIndex < matrix.getNumOfColumns(); ++colIndex) {
                transposeElements[colIndex][rowIndex] = matrix.getElements()[rowIndex][colIndex];
            }
        }
        Matrix transposeMatrix = new Matrix(transposeElements, matrix.getNumOfColumns(), matrix.getNumOfRows());
        return transposeMatrix;
    }

    public static Matrix getAdjointMatrix(Matrix matrix) {
        Matrix coFactorMatrix = getCoFactorMatrix(matrix);
        Matrix adjointMatrix = getTransposeMatrix(coFactorMatrix);
        return adjointMatrix;
    }

    public static Matrix multiplyByCoefficient(Matrix matrix, double coefficient) {
        double resultElements[][] = new double[matrix.getNumOfRows()][matrix.getNumOfColumns()];
        int rowIndex, colIndex;
        for (rowIndex = 0; rowIndex < matrix.getNumOfRows(); ++rowIndex) {
            for (colIndex = 0; colIndex < matrix.getNumOfColumns(); ++colIndex) {
                resultElements[rowIndex][colIndex] = coefficient * matrix.getElements()[rowIndex][colIndex];
            }
        }

        return new Matrix(resultElements, matrix.getNumOfRows(), matrix.getNumOfColumns());

    }

    public static Matrix getInverseOfMatrix(Matrix matrix) {
        double determinant = getDeterminant(matrix);
        if (determinant == 0.0) {
            return null;
        }
        Matrix adjointMatrix = getAdjointMatrix(matrix);
        return multiplyByCoefficient(adjointMatrix, 1 / determinant);
    }

    public static Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getNumOfColumns() != matrix2.getNumOfRows()) {
            return null;
        }
        double resultElements[][] = new double[matrix1.getNumOfRows()][matrix2.getNumOfColumns()];
        int i, j, k;
        for (i = 0; i < matrix1.getNumOfRows(); ++i) {
            for (j = 0; j < matrix2.getNumOfColumns(); ++j) {
                resultElements[i][j] = 0;
                for (k = 0; k < matrix2.getNumOfRows(); ++k) {
                    resultElements[i][j] += matrix1.getElements()[i][k] * matrix2.getElements()[k][j];
                }
            }
        }

        return new Matrix(resultElements, matrix1.getNumOfRows(), matrix2.getNumOfColumns());
    }

}