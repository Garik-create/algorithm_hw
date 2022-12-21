import java.util.*;

public class StringListImpl implements StringList {

    private final String[] strings;

    private int size;

    public StringListImpl() {
        this.strings = new String[10];
    }

    public StringListImpl(int initSize) {
        this.strings = new String[initSize];
    }


    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        strings[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateItem(item);
        validateIndex(index);
        validateSize();
        if (strings.length == size) {
            strings[size++] = item;
            return item;
        }
        System.arraycopy(strings, index, strings, index + 1, size - index);
        strings[index] = item;
        size++;

        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        strings[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        if (index != size) {
            System.arraycopy(strings, index + 1, strings, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = strings[index];
        if (index != size) {
            System.arraycopy(strings, index + 1, strings, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {

            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return strings[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(strings, size);
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void validateSize() {
        if (size == strings.length) {
            throw new StringsIsFullException();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(strings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringListImpl that = (StringListImpl) o;
        return size == that.size && Arrays.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(strings);
        return result;
    }


    public static void main(String[] args) {
////        String[] strings = {"", "", "", "", ""};
//
//        System.out.println("1 case");
//        StringListImpl stringsArray = new StringListImpl();
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        stringsArray.add("first");
//        System.out.println("Массив строк полученный = " + stringsArray);
//        System.out.println();
//
//        System.out.println("2 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        stringsArray.add(1, "second");
//        System.out.println("Массив строк полученный = " + stringsArray);
//        System.out.println();
//
//        System.out.println("3 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        stringsArray.set(2, "third");
//        System.out.println("Массив строк полученный = " + stringsArray);
//        System.out.println();
//
//        System.out.println("4 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        stringsArray.remove("third");
//        System.out.println("Массив строк полученный = " + stringsArray);
//        System.out.println();
//
//        System.out.println("5 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        stringsArray.remove(0);
//        System.out.println("Массив строк полученный = " + stringsArray);
//        System.out.println();
//
//        System.out.println("6 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        String s = "second";
//
//        System.out.println(stringsArray.contains(s));
//        System.out.println();
//
//        System.out.println("7 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        System.out.println("Индекс элемента " + s + " = " + stringsArray.indexOf(s));
//        System.out.println();
//
//        System.out.println("8 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        System.out.println("Индекс элемента " + s + " с конца = " + stringsArray.lastIndexOf(s));
//        System.out.println();
//
//        System.out.println("9 case");
//        int index = 0;
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        System.out.printf("Элемент по индексу %d = %s%n", index, stringsArray.get(index));
//
//        System.out.println("10 case");
//        System.out.println("Массив строк изначальный = " + stringsArray);
//        String[] otherStrings = {"second", "null", "null", "null", "null"};
//        StringListImpl otherStringsList = new StringListImpl();
//
//        System.out.println("Другой массив строк = " + Arrays.toString(otherStrings));
//        stringsArray.equals(otherStringsList);
    }


}

