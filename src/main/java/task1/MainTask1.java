package main.java.task1;

public class MainTask1 {

    public static void main(String[] args) {

        int[][] array = new int[5][5];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = randomValue(50 + i + j);
            }
        }
        printArray(array);
        System.out.println("Maximum value: " + maxValue(array));
        System.out.println("Minimum value: " + minValue(array));
        System.out.println("Average value: " + averageValue(array));
    }

    public static int maxValue(int[][] array) {
        int max = array[0][0];
        for (int[] row : array) {
            for (int columnElement : row) {
                if (max < columnElement) {
                    max = columnElement;
                }
            }
        }
        return max;
    }

    public static int minValue(int[][] array) {
        int min = array[0][0];
        for (int[] row : array) {
            for (int columnElement : row) {
                if (min > columnElement) {
                    min = columnElement;
                }
            }
        }
        return min;
    }

    public static double averageValue(int[][] array) {
        double sum = 0;
        for (int[] row : array) {
            for (int columnElement : row) {
                sum += columnElement;
            }
        }
        return sum / quantityArrayElements(array);
    }

    private static int quantityArrayElements(int[][] array) {
        int quantity = 0;
        for (int[] row : array) {
            quantity += row.length;
        }
        return quantity;
    }

    public static int randomValue(int maxValue) {
        int value;
        value = (int) (System.currentTimeMillis() % maxValue);
        value = (value * 32719 + 3) % 32749;
        return value % maxValue;
    }

    private static void printArray(int[][] array) {
        for (int[] ints : array) {
            StringBuilder builder = new StringBuilder("[");
            for (int j = 0; j < array.length; j++) {
                if (j < ints.length - 1) {
                    builder.append(ints[j]).append(", ");
                } else {
                    builder.append(ints[j]).append("]");
                }
            }
            System.out.println(builder);
        }
        System.out.println();
    }
}

