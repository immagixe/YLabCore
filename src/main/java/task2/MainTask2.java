package main.java.task2;

public class MainTask2 {

    public static void main(String[] args) {
        int[] array = new int[]{5, 6, 3, 2, 5, 1, 4, 9};
        System.out.println("Source array: " + arrayToString(array));

        quicksort(array, 0, array.length - 1);
        System.out.println("Sorted array: " + arrayToString(array));
        testSortedArray(array);
    }

    public static void quicksort(int[] array, int leftBorder, int rightBorder) {

        int leftPointer = leftBorder;
        int rightPointer = rightBorder;
        int pivot = array[(leftPointer + rightPointer) / 2];

        while (leftPointer <= rightPointer) {

            while (array[leftPointer] < pivot) {
                leftPointer++;
            }
            while (array[rightPointer] > pivot) {
                rightPointer--;
            }

            if (leftPointer <= rightPointer) {
                swap(array, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }

        if (leftPointer < rightBorder) {
            quicksort(array, leftPointer, rightBorder);
        }

        if (leftBorder < rightPointer) {
            quicksort(array, leftBorder, rightPointer);
        }
    }

    private static void swap(int[] array, int leftPointer, int rightPointer) {
        int temp = array[leftPointer];
        array[leftPointer] = array[rightPointer];
        array[rightPointer] = temp;
    }

    public static String arrayToString(int[] array) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                builder.append(array[i]).append(", ");
            } else {
                builder.append(array[i]).append("]");
            }
        }
        return builder.toString();
    }

    public static void testSortedArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            assert array[i] <= array[i + 1] : "Array is not sorted!";
        }
    }
}

