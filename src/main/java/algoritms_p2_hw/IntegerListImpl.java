package algoritms_p2_hw;

import algoritms_p2_hw.exceptions.ElementNotFoundException;
import algoritms_p2_hw.exceptions.IntegersIsFullException;
import algoritms_p2_hw.exceptions.InvalidIndexException;
import algoritms_p2_hw.exceptions.NullItemException;

import java.util.*;


public class IntegerListImpl implements IntegerList {

    private Integer[] integers;

    private int size;


    public IntegerListImpl(int initSize) {
        this.integers = new Integer[initSize];
    }


    void grow() {
        integers = Arrays.copyOf(integers, size + size / 2);
    }


    private boolean binarySearch(int item) {
        int min = 0;
        var integers1 = Arrays.copyOf(integers, integers.length);
        int max = integers1.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == integers1[mid]) {
                return true;
            }

            if (item < integers1[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        validateSize();
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
        var integers1 = Arrays.copyOf(integers, integers.length);
        quickSort(integers1,0,integers1.length-1);
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
            grow();
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
        Integer[] randomArray = arrayGenerator(arraySize);

        var counterForBubbleSort = timeCounterForBubbleSort(randomArray);
        var counterForSelectionSort = timeCounterForSelectionSort(randomArray);
        var counterForInsertionSort = timeCounterForInsertionSort(randomArray);

        String choice = bestSortChoice("bubbleSort", counterForBubbleSort,
                "insertionSort", counterForInsertionSort,
                "selectionSort", counterForSelectionSort);

        System.out.println("Быстрая сортировка = " + choice);

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
        long count = 0;
        long avgTime = 0;
        long sumTimes = 0;
        int i = 0;
        while (i < 1000) {
            Integer[] arrayToSort = Arrays.copyOf(arr, arr.length);
            long start = System.currentTimeMillis();
            bubbleSort(arrayToSort);
            count = System.currentTimeMillis() - start;
            sumTimes = sumTimes + count;
            i++;
        }
        avgTime = sumTimes / i;
        System.out.println("Время пузырьковой сортировки: " + avgTime);
        return avgTime;
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
        long count = 0;
        long avgTime = 0;
        long sumTimes = 0;
        int i = 0;
        while (i < 1000) {
            Integer[] arrayToSort = Arrays.copyOf(arr, arr.length);
            long start = System.currentTimeMillis();
            selectionSort(arrayToSort);
            count = System.currentTimeMillis() - start;
            sumTimes = sumTimes + count;
            i++;
        }
        avgTime = sumTimes / i;
        System.out.println("Время сортировки выбором: " + avgTime);
        return avgTime;
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
        long count = 0;
        long avgTime = 0;
        long sumTimes = 0;
        int i = 0;
        while (i < 1000) {
            Integer[] arrayToSort = Arrays.copyOf(arr, arr.length);
            long start = System.currentTimeMillis();
            insertionSort(arrayToSort);
            count = System.currentTimeMillis() - start;
            sumTimes = sumTimes + count;
            i++;
        }
        avgTime = sumTimes / i;
        System.out.println("Время сортировки вставкой: " + avgTime);
        return avgTime;
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
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
