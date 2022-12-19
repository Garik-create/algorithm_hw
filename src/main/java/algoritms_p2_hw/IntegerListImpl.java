package algoritms_p2_hw;

import algoritms_p2_hw.exceptions.ElementNotFoundException;
import algoritms_p2_hw.exceptions.IntegersIsFullException;
import algoritms_p2_hw.exceptions.InvalidIndexException;
import algoritms_p2_hw.exceptions.NullItemException;

import java.util.*;


public class IntegerListImpl implements IntegerList {

    private final Integer[] integers;

    private int size;

    public IntegerListImpl() {
        this.integers = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        this.integers = new Integer[initSize];
    }

    public static String choice;


    private void bestSortMethod() {
        switch (choice) {
            case "bubbleSort" -> {
                bubbleSort(integers);
            }
            case "insertionSort" -> {
                insertionSort(integers);
            }
            case "selectionSort" -> selectionSort(integers);
        }

    }
    private boolean binarySearch(int item) {
        int min = 0;
        int max = integers.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == integers[mid]) {
                return true;
            }

            if (item < integers[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        integers[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        validateSize();
        if (integers.length == size) {
            integers[size++] = item;
            return item;
        }
        System.arraycopy(integers, index, integers, index + 1, size - index);
        integers[index] = item;
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        integers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        if (index != size) {
            System.arraycopy(integers, index + 1, integers, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = integers[index];
        if (index != size) {
            System.arraycopy(integers, index + 1, integers, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        bestSortMethod();
        return binarySearch(item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {

            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }

    private void validateSize() {
        if (size == integers.length) {
            throw new IntegersIsFullException();
        }
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(integers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerListImpl that = (IntegerListImpl) o;
        return size == that.size && Arrays.equals(integers, that.integers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(integers);
        return result;
    }

    public static void main(String[] args) {

        int arraySize = 100000;
        arrayGenerator(arraySize);
        Integer[] randomArray1 = arrayGenerator(arraySize);
        Integer[] randomArray2 = Arrays.copyOf(randomArray1, arraySize);
        Integer[] randomArray3 = Arrays.copyOf(randomArray1, arraySize);

        var counterForBubbleSort = timeCounterForBubbleSort(randomArray1);
        var counterForSelectionSort = timeCounterForSelectionSort(randomArray2);
        var counterForInsertionSort = timeCounterForInsertionSort(randomArray3);

        choice = bestSortChoice("bubbleSort", counterForBubbleSort,
                "insertionSort", counterForInsertionSort,
                "selectionSort", counterForSelectionSort);

        System.out.println("choice = " + choice);

    }

    private static Integer[] arrayGenerator(int size) {
        var integers = new Integer[size];
        for (int i = 0; i < integers.length; i++) {
            var random = (int) (Math.random() * size);
            integers[i] = random;
        }
        return integers;
    }

    private static void swapElements(Integer[] arr, Integer indexA, Integer indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static long timeCounterForBubbleSort(Integer[] arr) {
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long count = System.currentTimeMillis() - start;
        System.out.println(count);
        return count;
    }

    public static void selectionSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static long timeCounterForSelectionSort(Integer[] arr) {
        long start = System.currentTimeMillis();
        selectionSort(arr);
        long count = System.currentTimeMillis() - start;
        System.out.println(count);
        return count;
    }

    public static void insertionSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static long timeCounterForInsertionSort(Integer[] arr) {
        long start = System.currentTimeMillis();
        insertionSort(arr);
        long count = System.currentTimeMillis() - start;
        System.out.println(count);
        return count;
    }

    public static String bestSortChoice(String count1,
                                        long counter1,
                                        String count2,
                                        long counter2,
                                        String count3,
                                        long counter3) {
        long minTimeCounter;
        if (counter1 < counter2 && counter1 < counter3) {
            minTimeCounter = counter1;
        } else {
            minTimeCounter = Math.min(counter2, counter3);
        }
        if (minTimeCounter == counter1) {
            return count1;
        } else if (minTimeCounter == counter2) {
            return count2;
        } else {
            return count3;
        }
    }
}
