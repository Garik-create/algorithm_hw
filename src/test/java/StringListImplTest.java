import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class StringListImplTest {

    private StringListImpl actual;
    private StringListImpl expected;

    private static final String S1 = "first";
    private static final String S2 = "second";
    private static final String S3 = "third";
    private static final String S4 = "forth";
    private static final String S5 = "fifth";

    @BeforeEach
    public void setUp() {
        expected = new StringListImpl(10);
        expected.add(S1);
        expected.add(S2);
        expected.add(S3);
        expected.add(S4);
        expected.add(S5);
        actual = new StringListImpl(10);
        actual.add(S1);
        actual.add(S2);
        actual.add(S3);
        actual.add(S4);
        actual.add(S5);
    }

    @Test
    void add() {
        Object[] expected = List.of(S1, S2, S3, S4, S5,"new").toArray();
        actual.add("new");
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void addByIndex() {
        Object[] expected = List.of(S1, "new", S2, S3, S4, S5).toArray();
        actual.add(1, "new");
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void set() {
        Object[] expected = List.of("new", S2, S3, S4, S5).toArray();
        actual.set(0,"new");
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void remove() {
        Object[] expected = List.of(S1, S3, S4, S5).toArray();
        actual.remove(S2);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void removeByIndex() {
        Object[] expected = List.of(S1, S2, S3, S4).toArray();
        actual.remove(4);
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void contains() {
        expected.contains(S3);
        Assertions.assertTrue(actual.indexOf(S3) != -1);
    }

    @Test
    void indexOf() {
        Assertions.assertEquals(1,actual.indexOf(S2));
    }

    @Test
    void lastIndexOf() {
        actual.add(S2);
        Assertions.assertEquals(5,actual.lastIndexOf(S2));
    }

    @Test
    void get() {
        Assertions.assertEquals(actual.get(0),S1);
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
        Assertions.assertFalse(expected.isEmpty());

    }

    @Test
    void clear() {
        expected.clear();
        Assertions.assertTrue(expected.isEmpty());

    }

    @Test
    void toArray() {
        String[] strings = expected.toArray();
        String[] newStrings = {"first", "second", "third", "forth", "fifth"};
        Assertions.assertArrayEquals(strings,newStrings);
    }

    @Test
    void shouldTrowElementNotFoundExceptionWhenRemoveByItem() {
        Assertions.assertThrows(ElementNotFoundException.class,
                () -> expected.remove("toRemove"));
    }

    @Test
    void shouldTrowStringsIsFullExceptionWhenValidateSize() {
        expected.add("sixth");
        expected.add("seven");
        expected.add("eighth");
        expected.add("ninth");
        expected.add("tenth");
        Assertions.assertThrows(StringsIsFullException.class,
                () -> expected.add("test"));

    }

    @Test
    void shouldThrowInvalidIndexExceptionWhenValidateIndex() {
        Assertions.assertThrows(InvalidIndexException.class,
                () -> expected.add(11, "test2"));
    }

    @Test
    void shouldThrowNullItemExceptionWhenValidateItem() {
        Assertions.assertThrows(NullItemException.class,
                () -> expected.set(1, null));
    }
}