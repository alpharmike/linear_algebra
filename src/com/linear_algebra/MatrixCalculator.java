package com.linear_algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixCalculator {
    public static double getDeterminant(Matrix matrix) {
        if (!matrix.isSquare()) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return calcDeterminantRec(matrix);
        }
    }

    private static double calcDeterminantRec(Matrix matrix) {
        if (matrix.getNumOfRows() == 1 && matrix.getNumOfColumns() == 1) {
            return matrix.getElements()[0][0];
        }
        if (matrix.getNumOfRows() == 2 && matrix.getNumOfColumns() == 2) {
            return matrix.getElements()[0][0] * matrix.getElements()[1][1] - matrix.getElements()[0][1] * matrix.getElements()[1][0];
        }

        double initialValue = 0.0;

        int i, j;
        for (i = 0; i < matrix.getNumOfColumns(); ++i) {
            Matrix modMat = eliminateMatrix(matrix, 1, i + 1);
            initialValue += Math.pow(-1, i) * matrix.getElements()[0][i] * calcDeterminantRec(modMat);

        }
        return initialValue;
    }


    public static Matrix eliminateMatrix(Matrix matrix, int rowNumber, int colNumber) {
//        double modifiedMatrix = new Matrix();
        double[][] matrixElements = new double[matrix.getNumOfRows() - 1][matrix.getNumOfColumns() - 1];
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
        if (matrix == null) {
            return null;
        }

        int rowIndex, colIndex;
        double[][] coFactorElements = new double[matrix.getNumOfRows()][matrix.getNumOfColumns()];
        for (rowIndex = 0; rowIndex < matrix.getNumOfRows(); ++rowIndex) {
            for (colIndex = 0; colIndex < matrix.getNumOfColumns(); ++colIndex) {
                coFactorElements[rowIndex][colIndex] = Math.pow(-1, rowIndex + colIndex) * calcDeterminantRec(eliminateMatrix(matrix, rowIndex + 1, colIndex + 1));
            }
        }
        Matrix coFactorMatrix = new Matrix(coFactorElements, matrix.getNumOfRows(), matrix.getNumOfColumns());
        return coFactorMatrix;
    }

    public static Matrix getTransposeMatrix(Matrix matrix) {
        double[][] transposeElements = new double[matrix.getNumOfColumns()][matrix.getNumOfRows()];
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

    public static Matrix subtractMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            return null;
        }
        if (!dimensionsMatched(matrix1, matrix2)) {
            return null;
        }

        double resultElements[][] = new double[matrix1.getNumOfRows()][matrix2.getNumOfColumns()];
        int rowIndex, colIndex;
        for (rowIndex = 0; rowIndex < matrix1.getNumOfRows(); ++rowIndex) {
            for (colIndex = 0; colIndex < matrix1.getNumOfColumns(); ++colIndex) {
                resultElements[rowIndex][colIndex] = matrix1.getElements()[rowIndex][colIndex] - matrix2.getElements()[rowIndex][colIndex];
            }
        }

        return new Matrix(resultElements, matrix1.getNumOfRows(), matrix1.getNumOfColumns());
    }

    public static Matrix getInverseOfMatrix(Matrix matrix) {
        if (matrix == null) {
            return null;
        }

        double determinant = getDeterminant(matrix);
        System.out.println("det");
        System.out.println(determinant);
        if (determinant == 0.0) {
            return null;
        }

        if (matrix .getNumOfRows() == 1 && matrix.getNumOfColumns() == 1) {
            double[][] elements = new double[matrix.getNumOfRows()][matrix.getNumOfColumns()];
            elements[0][0] = 1 / matrix.getElements()[0][0];
            return new Matrix(elements, matrix.getNumOfRows(), matrix.getNumOfColumns());
        }

        Matrix adjointMatrix = getAdjointMatrix(matrix);
        System.out.println("adjoin");
        adjointMatrix.show();
        return multiplyByCoefficient(adjointMatrix, 1 / determinant);
    }

    public static Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            return null;
        }
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

    public static double calculateNorm(Matrix matrix) {
        if (!matrix.isVector()) {
            return -1;
        }
        double sumOfSquares = 0.0;
        int rowIndex;
        for (rowIndex = 0; rowIndex < matrix.getNumOfRows(); ++rowIndex) {
            sumOfSquares += Math.pow(matrix.getElements()[rowIndex][0], 2);
        }
        double norm = Math.sqrt(sumOfSquares);
        return norm;
    }

    public static Matrix replaceColumn(Matrix coefficientMatrix, Matrix rightHandSide, int colNumber) {
        if (coefficientMatrix == null || rightHandSide == null) {
            return null;
        }
        int colIndex, rowIndex;
        double tempElements[][] = new double[coefficientMatrix.getNumOfRows()][coefficientMatrix.getNumOfColumns()];

        for (colIndex = 0; colIndex < coefficientMatrix.getNumOfColumns(); ++colIndex) {
            for (rowIndex = 0; rowIndex < coefficientMatrix.getNumOfRows(); ++rowIndex) {
                if (colNumber != colIndex + 1) {
                    tempElements[rowIndex][colIndex] = coefficientMatrix.getElements()[rowIndex][colIndex];
                } else {
                    tempElements[rowIndex][colIndex] = rightHandSide.getElements()[rowIndex][0];
                }
            }
        }

        Matrix tempMatrix = new Matrix(tempElements, coefficientMatrix.getNumOfRows(), coefficientMatrix.getNumOfColumns());
        return tempMatrix;
    }

    public static boolean dimensionsMatched(Matrix matrix1, Matrix matrix2) {
        return matrix1.getNumOfRows() == matrix2.getNumOfRows() && matrix1.getNumOfColumns() == matrix2.getNumOfColumns();
    }

    public static double calcDotProduct(Matrix matrix1, Matrix matrix2) {
        int rowIndex, colIndex;
        double result = 0.0;
        for (rowIndex = 0; rowIndex < matrix1.getNumOfRows(); ++rowIndex) {
            for (colIndex = 0; colIndex < matrix1.getNumOfColumns(); ++colIndex) {
                result += matrix1.getElements()[rowIndex][colIndex] * matrix2.getElements()[rowIndex][colIndex];
            }
        }

        return result;
    }

    public static Map<String, Object> applyQRFactorization(Matrix matrix) {
        List<Integer> dependentCols = new ArrayList<>(); // we use an array to store the column numbers which are dependent
        Matrix transposedMatrix = getTransposeMatrix(matrix); // we now have the columns of the original matrix in rows, making the operation simpler

        double[][] QElements = new double[transposedMatrix.getNumOfRows()][transposedMatrix.getNumOfColumns()];
        int rowIndex, prevIndex;

        /* the loop does the main operation for calculating the orthogonal matrix, using the columns of the original matrix to obtain
        * the orthonormal unit vector for the orthogonal matrix  */
        for (rowIndex = 0; rowIndex < transposedMatrix.getNumOfRows(); ++rowIndex) {
            double[][] currVectorElements = new double[1][transposedMatrix.getNumOfColumns()];
            currVectorElements[0] = transposedMatrix.getElements()[rowIndex];
            Matrix currVector = new Matrix(currVectorElements, 1, transposedMatrix.getNumOfColumns());
            Matrix originalMatrix = new Matrix(currVectorElements.clone(), 1, transposedMatrix.getNumOfColumns());
            for (prevIndex = 0; prevIndex < rowIndex; ++prevIndex) {
                if (dependentCols.contains(prevIndex)) { // ignoring dependent columns in calculation of the orthonormal vector for Q
                    continue;
                }

                double[][] unitVectorElements = new double[1][transposedMatrix.getNumOfColumns()];
                unitVectorElements[0] = QElements[prevIndex];
                Matrix unitVector = new Matrix(unitVectorElements, 1, transposedMatrix.getNumOfColumns());
                currVector = subtractMatrices( // the orthonormal vector is calculated here for matrix Q
                        currVector,
                        multiplyByCoefficient(
                                unitVector,
                                calcDotProduct(originalMatrix, unitVector)
                        )
                );
            }
            if (calculateNorm(getTransposeMatrix(currVector)) >= 1e-40) { // get the orthonormal vector for matrix Q, if the column is independent
                QElements[rowIndex] = multiplyByCoefficient(currVector, 1 / calculateNorm(getTransposeMatrix(currVector))).getElements()[0];
            } else { // if the norm of the vector is too close too zero, this suggest that the column is probably dependent
                QElements[rowIndex] = null;
                dependentCols.add(rowIndex);
            }
        }

        int index;

        int rowDimension = transposedMatrix.getNumOfRows() - dependentCols.size();
        double[][] newQElements = new double[rowDimension][transposedMatrix.getNumOfColumns()];

        int enteredIndex = 0;
        for (index = 0; index < QElements.length; ++index) { // reducing matrix Q to a lower dimension, if the original matrix has dependent columns
            if (QElements[index] != null) {
                newQElements[enteredIndex] = QElements[index];
                ++enteredIndex;
            }
        }

        enteredIndex = 0;
        double[][] newTransposedElements = new double[rowDimension][transposedMatrix.getNumOfColumns()];

        for (index = 0; index < transposedMatrix.getNumOfRows(); ++index) { // removing dependent columns of the original matrix
            if (QElements[index] != null) {
                newTransposedElements[enteredIndex] = transposedMatrix.getElements()[index];
                ++enteredIndex;
            }
        }

        Matrix newTransposedMatrix = new Matrix(newTransposedElements, rowDimension, transposedMatrix.getNumOfColumns());

        Matrix R = getUpperTriangularMatrix(newTransposedMatrix, newQElements); // calculating the square upper triangular matrix
        Matrix Q = new Matrix(newQElements, rowDimension, transposedMatrix.getNumOfColumns());
        Q = getTransposeMatrix(Q);
        Map<String, Object> QR = new HashMap<>();
        QR.put("Q", Q);
        QR.put("R", R);
        QR.put("D", dependentCols);
        return QR;
//        return null;
    }

    private static Matrix getUpperTriangularMatrix(Matrix matrix, double[][] unitVectors) {
        /* a1e1 a2e1 ... ane1
            0   a2e2 ... ane2
            0 0 0 0  ... anen is the form of the R matrix */
        int rowIndex, colIndex;
        double[][] upperTriangularElements = new double[matrix.getNumOfRows()][matrix.getNumOfRows()];
        for (rowIndex = 0; rowIndex < matrix.getNumOfRows(); ++rowIndex) {
            double[][] unitVectorElements = new double[1][matrix.getNumOfColumns()];
            unitVectorElements[0] = unitVectors[rowIndex];
            Matrix unitVector = new Matrix(unitVectorElements, 1, matrix.getNumOfColumns());
            for (colIndex = 0; colIndex < matrix.getNumOfRows(); ++colIndex) {
                if (colIndex < rowIndex) {
                    upperTriangularElements[rowIndex][colIndex] = 0;
                } else { // calculating the dot product of the column in original matrix and the orthonormal column vector in matrix Q
                    double[][] currVectorElements = new double[1][matrix.getNumOfColumns()];
                    currVectorElements[0] = matrix.getElements()[colIndex];
                    Matrix currVector = new Matrix(currVectorElements, 1, matrix.getNumOfColumns());
                    upperTriangularElements[rowIndex][colIndex] = calcDotProduct(currVector, unitVector);
                }
            }
        }

        Matrix upperTriangularMatrix = new Matrix(upperTriangularElements, matrix.getNumOfRows(), matrix.getNumOfRows());
        return upperTriangularMatrix;
    }

    public static boolean validSolution(Matrix trueY, Matrix predictedY) {
        /* the function checks for the difference between the given right hand side matrix, and the one which we
        obtained by multiplying the original matrix at the solutions we have got by applying QR Factorization, and we either
         accept or ignore the solutions based on this difference */
        Matrix difference = MatrixCalculator.subtractMatrices(trueY, predictedY);
        double errorNorm = calculateNorm(difference);
        System.out.println("Error Norm: " + errorNorm);
//        int index;
//        for (index = 0; index < difference.getNumOfRows(); ++index) {
//            if (Math.abs(difference.getElements()[index][0]) > 1e-10) {
//                return false;
//            }
//        }
        if (errorNorm > 1e-6) {
            return false;
        }
        return true;
    }

    public static double[] applyBackSubstitution(Matrix upperTriangular, Matrix rightHandSide) {
        // in order to decrease the computation time we should spend for calculating determinant and inverse of a matrix, we could use back substitution
        int index, nextSolIndex;
        double[] solutions = new double[upperTriangular.getNumOfColumns()];
        for (index = upperTriangular.getNumOfRows() - 1; index >= 0; --index) {
            double rightY = rightHandSide.getElements()[index][0];
            for (nextSolIndex = upperTriangular.getNumOfRows() - 1; nextSolIndex > index; --nextSolIndex) {
                rightY -= (upperTriangular.getElements()[index][nextSolIndex] * solutions[nextSolIndex]);
            }
            solutions[index] = rightY / upperTriangular.getElements()[index][nextSolIndex]; // now nextSolIndex = index
        }

        return solutions;


    }

}
