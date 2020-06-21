package com.linear_algebra;

import com.linear_algebra.Matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutation {
    Permutation() {

    }

    public static Map<Integer, Integer> findMapping(int[] matrix) {
        Map<Integer, Integer> permMapping = new HashMap<>();
        int index;
        for (index = 0; index < matrix.length; ++index) {
            permMapping.put(index, matrix[index]);
        }
        return permMapping;
    }

    public static int findPermutation(int[] matrix) {
        Map<Integer, Integer> permMapping = findMapping(matrix);
        int index, max;
        ArrayList<Integer> cycles = new ArrayList<>();
        for (index = 0; index < matrix.length; ++index) {
            max = 0;
            if (permMapping.containsKey(index)) {
                ++max;
                int value = permMapping.get(index);
                permMapping.remove(index);
                int key ;
                while (permMapping.containsKey(value)) {
                    key = value;
                    value = permMapping.get(key);
                    permMapping.remove(key);
                    ++max;
                }
                cycles.add(max);
            }
        }

        int lcm = lcm_of_array_elements(cycles);
        return lcm;
    }

    public static ArrayList<Integer> findPermutationCycles(int[] matrix) {
        Map<Integer, Integer> permMapping = findMapping(matrix);
        int index, max;
        ArrayList<ArrayList<Integer>> cycles = new ArrayList<>();
        for (index = 0; index < matrix.length; ++index) {
            max = 0;
            if (permMapping.containsKey(index)) {
                cycles.add(new ArrayList<>());

                ++max;
                int value = permMapping.get(index);
                permMapping.remove(index);
//                System.out.println(cycles.get(index));
                cycles.get(cycles.size() - 1).add(index);
                int key;
                while (permMapping.containsKey(value)) {
                    cycles.get(cycles.size() - 1).add(value);
                    key = value;
                    value = permMapping.get(key);
                    permMapping.remove(key);
                    ++max;
                }
            }
        }
//        showCycles(cycles);
//        return null;
        ArrayList<ArrayList<Integer>> evenCycles = modifyEvenCycles(cycles);
//        showCycles(evenCycles);
        if (evenCycles == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> oddCycles = modifyOddCycles(cycles);
        ArrayList<ArrayList<Integer>> finalCycles = new ArrayList<>();


        for (index = 0; index < evenCycles.size(); ++index) {
            finalCycles.add(evenCycles.get(index));
        }
        for (index = 0; index < oddCycles.size(); ++index) {
            finalCycles.add(oddCycles.get(index));
        }
        return flatten(finalCycles);
    }



    public static ArrayList<ArrayList<Integer>> getEvenCycles(ArrayList<ArrayList<Integer>> cycles) {
        ArrayList<ArrayList<Integer>> evenCycles = new ArrayList<>();
        int index;
        for (index = 0; index < cycles.size(); ++index) {
            if (cycles.get(index).size() % 2 == 0) {
                evenCycles.add(cycles.get(index));
            }
        }
        return evenCycles;
    }

    public static ArrayList<ArrayList<Integer>> getOddCycles(ArrayList<ArrayList<Integer>> cycles) {
        ArrayList<ArrayList<Integer>> oddCycles = new ArrayList<>();
        int index;
        for (index = 0; index < cycles.size(); ++index) {
            if (cycles.get(index).size() % 2 == 1) {
                oddCycles.add(cycles.get(index));
            }
        }
        return oddCycles;
    }

    public static ArrayList<Integer> flatten(ArrayList<ArrayList<Integer>> cycles) {
        ArrayList<Integer> newList = new ArrayList<>();
        int i,j;
        for (i = 0;i < cycles.size(); ++i) {
            for (j = 0; j < cycles.get(i).size(); ++j) {
                newList.add(cycles.get(i).get(j));
            }
        }
        return newList;
    }

    public static ArrayList<ArrayList<Integer>> sortEvenCycles(ArrayList<ArrayList<Integer>> evenCycles) {
        ArrayList<ArrayList<Integer>> sortedEvenCycles = new ArrayList<>();
        int index, cycleSize, maxSize;
        cycleSize = 2;
        maxSize = 0;
        for (index = 0; index < evenCycles.size(); ++index) {
            if (evenCycles.get(index).size() > maxSize) {
                maxSize = evenCycles.get(index).size();
            }
        }
        while (cycleSize <= maxSize) {
            for (index = 0; index < evenCycles.size(); ++index) {
                if (evenCycles.get(index).size() == cycleSize) {
                    sortedEvenCycles.add(evenCycles.get(index));
                }
            }
            cycleSize += 2;
        }

        return sortedEvenCycles;
    }

    public static ArrayList<Integer> getEvenCyclesLength(ArrayList<ArrayList<Integer>> cycles) {
        ArrayList<Integer> evenCyclesLength = new ArrayList<>();
        int index, maxLen;
        maxLen = 0;
        for (index = 0; index < cycles.size(); ++index) {
            maxLen = Math.max(cycles.get(index).size(), maxLen);
        }

        for (index = 0; index < maxLen / 2; ++index) {
            evenCyclesLength.add(0);
        }
        int size = 2;
        int currSize = 0;
        while (size <= maxLen) {
            currSize = 0;
            for (index = 0; index < cycles.size(); ++index) {
                if (cycles.get(index).size() == size) {
                    evenCyclesLength.set(size / 2 - 1, ++currSize);
                }
            }
            size += 2;
        }
        return evenCyclesLength;
    }

    public static ArrayList<ArrayList<Integer>> modifyEvenCycles(ArrayList<ArrayList<Integer>> cycles) {
        int i, j;
        ArrayList<ArrayList<Integer>> evenCycles = sortEvenCycles(getEvenCycles(cycles));
        ArrayList<ArrayList<Integer>> modifiedEvenCycles = new ArrayList<>();
        ArrayList<Integer> evenCycleLengths = getEvenCyclesLength(evenCycles);
        int x;
        for (x = 0; x < evenCycleLengths.size(); ++x) {
            if (evenCycleLengths.get(x) % 2 == 1) {
                return null;
            }
        }
//        if (evenCycles.size() != 0) {
//            return null;
//        }
        int prevLength = 0;
        i = j = 0;
        int index, step;

        for (index = 0; index < evenCycleLengths.size(); ++index) {
            if (evenCycleLengths.get(index) == 0) {
                evenCycleLengths.remove(index);
                --index;
            }
        }
        for (index = 0; index < evenCycleLengths.size(); ++index) {
            step = evenCycleLengths.get(index) / 2;
            modifiedEvenCycles.add(new ArrayList<>());
            j = 0;
            while (j < step) {
                int initialIndex = prevLength + 2 * j;
                int newIndex;
                for (newIndex = 0; newIndex < evenCycles.get(index).size(); ++newIndex) {
                    if (modifiedEvenCycles.get(index) == null) {
                        modifiedEvenCycles.set(index, new ArrayList<>());
                    }
                    modifiedEvenCycles.get(index).add(evenCycles.get(initialIndex).get(newIndex));
                    modifiedEvenCycles.get(index).add(evenCycles.get(initialIndex + 1).get(newIndex));
                }
                ++j;
            }
            prevLength = evenCycleLengths.get(index);
        }
        return modifiedEvenCycles;
    }

    public static ArrayList<ArrayList<Integer>> modifyOddCycles(ArrayList<ArrayList<Integer>> cycles) {
        ArrayList<ArrayList<Integer>> oddCycles = getOddCycles(cycles);
        int index = 0;
        for (index = 0; index < oddCycles.size(); ++index) {
            oddCycles.set(index, mergeList(oddCycles.get(index)));
        }
        return oddCycles;

    }
    public static ArrayList<Integer> mergeList(ArrayList<Integer> list) {
        int index;
        int mid = list.size() / 2;
        ArrayList<Integer> merged = new ArrayList<>();
        for (index = 0; index < mid; ++index) {
            merged.add(list.get(index));
            merged.add(list.get(mid + 1 + index));
        }
        merged.add(list.get(mid));
        return merged;
    }

    public static int[] permutationSqrt(int[] permutationMatrix) {
//        int cycle_length = findPermutation(permutationMatrix);
//        if (cycle_length % 2 == 0) {
//            System.out.println("Error");
//            return null;
//        } else {
//            int power = (cycle_length + 1) / 2;
//            int index;
//            int[] resultMatrix = permutationMatrix.clone();
//            int[] resultMatrixClone = resultMatrix.clone();
//            while (power > 1) {
//                for (index = 0; index < permutationMatrix.length; ++index) {
//                    resultMatrix[index] = resultMatrixClone[permutationMatrix[index]];
//                }
//                resultMatrixClone = resultMatrix.clone();
//                --power;
//            }
//            return resultMatrix;
//        }
        ArrayList<Integer> permutationList = findPermutationCycles(permutationMatrix);
        if (permutationList == null) {
            return null;
        }
        int[] finalResult = new int[permutationList.size()];
        int index;
        for (index = 0; index < permutationList.size(); ++index) {
            finalResult[permutationList.get((index + 1) % permutationList.size())] = permutationMatrix[permutationList.get(index)];
        }
        return finalResult;

    }

    public Matrix createPermutationMatrix(int[] matrix) {
        double[][] elements = new double[matrix.length][matrix.length];
        int rowIndex, colIndex;
        for (rowIndex = 0; rowIndex < matrix.length; ++rowIndex) {
            for (colIndex = 0; colIndex < matrix.length; ++colIndex) {
                if (colIndex == matrix[rowIndex]) {
                    elements[rowIndex][colIndex] = matrix[rowIndex];
                } else {
                    elements[rowIndex][colIndex] = 0;
                }
            }
        }

        return new Matrix(elements, matrix.length, matrix.length);
    }

    public static int lcm_of_array_elements(ArrayList<Integer> element_array) {
        int lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < element_array.size(); i++) {

                if (element_array.get(i) == 0) {
                    return 0;
                }
                else if (element_array.get(i) < 0) {
                    element_array.set(i, element_array.get(i) * (-1));
                }
                if (element_array.get(i) == 1) {
                    counter++;
                }
                if (element_array.get(i) % divisor == 0) {
                    divisible = true;
                    element_array.set(i, element_array.get(i) / divisor);
                }
            }

            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }

            if (counter == element_array.size()) {
                return lcm_of_array_elements;
            }
        }
    }

    public static void showPermutation(int[] matrix) {
        int index;
        for (index = 0; index < matrix.length; ++index) {
            System.out.print(matrix[index] + " ");
        }
        System.out.println("");
    }

    public static void showCycles(ArrayList<ArrayList<Integer>> cycles) {
        int index;
        for (index = 0; index < cycles.size(); ++index) {
            System.out.print(cycles.get(index) + " ");
        }
        System.out.println("");
    }
}
