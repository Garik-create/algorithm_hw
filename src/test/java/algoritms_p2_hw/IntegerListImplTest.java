package algoritms_p2_hw;

import algoritms_p2_hw.exceptions.ElementNotFoundException;
import algoritms_p2_hw.exceptions.IntegersIsFullException;
import algoritms_p2_hw.exceptions.InvalidIndexException;
import algoritms_p2_hw.exceptions.NullItemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private final IntegerListImpl actual = new IntegerListImpl(10);
    private final IntegerListImpl expected = new IntegerListImpl(10);
    private static final Integer I1 = 1;
    private static final Integer I2 = 2;
    private static final Integer I3 = 3;
    private static final Integer I4 = 4;
    private static final Integer I5 = 5;
    private static final Integer I6 = 6;

    @BeforeEach
    public void setUp() {
        actual.add(I1);
        actual.add(I2);
        actual.add(I3);
        actual.add(I4);
        actual.add(I5);

        expected.add(I1);
        expected.add(I2);
        expected.add(I3);
        expected.add(I4);
        expected.add(I5);
    }

    @Test
    void add() {
        Object[] expected = List.of(I1, I2, I3, I4, I5,6).toArray();
        actual.add(6);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void testAddByIndex() {
        Object[] expected = List.of(I1, 6, I2, I3, I4, I5).toArray();
        actual.add(1, 6);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void set() {
        Object[] expected = List.of(6, I2, I3, I4, I5).toArray();
        actual.set(0,6);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void remove() {
        Object[] expected = List.of(I1, I3, I4, I5).toArray();
        actual.remove(I2);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void removeByIndex() {
        Object[] expected = List.of(I1, I2, I3, I4).toArray();
        actual.remove(4);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void contains() {
        actual.add(10);
        actual.add(9);
        actual.add(8);
        actual.add(7);
        actual.add(6);
        Assertions.assertFalse(actual.contains(I3));
    }

    @Test
    void indexOf() {
        Assertions.assertEquals(1,actual.indexOf(I2));
    }

    @Test
    void lastIndexOf() {
        actual.add(I2);
        Assertions.assertEquals(5,actual.lastIndexOf(I2));
    }

    @Test
    void get() {
        Assertions.assertEquals(actual.get(0),I1);
    }

    @Test
    void testEquals() {
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void size() {
        Assertions.assertEquals(expected.size(),actual.size());
    }

    @Test
    void isEmpty() {
        Assertions.assertFalse(actual.isEmpty());
    }

    @Test
    void clear() {
        actual.clear();
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void toArray() {
        Integer[] expect = actual.toArray();
        Integer[] actual = {I1, I2, I3, I4, I5};
        Assertions.assertArrayEquals(expect, actual);
    }

    @Test
    void shouldTrowElementNotFoundExceptionWhenRemoveByItem() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> actual.remove(I6));
    }
@Test
    void shouldTrowStringsIsFullExceptionWhenValidateSize() {
        actual.add(6);
        actual.add(7);
        actual.add(8);
        actual.add(9);
        actual.add(10);
        Assertions.assertThrows(IntegersIsFullException.class,
                () -> actual.add(11));
    }

    @Test
    void shouldThrowInvalidIndexExceptionWhenValidateIndex() {
        Assertions.assertThrows(InvalidIndexException.class,
                () -> actual.add(11, 11));
    }

    @Test
    void shouldThrowNullItemExceptionWhenValidateItem() {
        Assertions.assertThrows(NullItemException.class,
                () -> expected.set(1, null));
    }

    @Test
    void bubbleSort() {

    }

    @Test
    void timeCounterForBubbleSort() {
    }

    @Test
    void selectionSort() {
    }

    @Test
    void timeCounterForSelectionSort() {
    }

    @Test
    void insertionSort() {
    }

    @Test
    void timeCounterForInsertionSort() {
    }

    @Test
    void bestSortChoice() {
    }
}