import java.util.*;

public class StringListImpl implements StringList {

    private final String[] strings;

    private int size;

//    List<String> stringsList = new ArrayList<>();

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
//        for (String s : strings) {
//            if (s != null) {
//                stringsList.add(s);
//            }
//        }
//        System.out.println("Список строк изначальный = " + stringsList);
//        stringsList.add(item);
//        System.out.println("Добавили элемент " + item);
//        System.out.println("Список строк полученный = " + stringsList);
//        stringsList.toArray(strings);
//        stringsList.clear();
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

//        for (String s : strings) {
//            if (s != null) {
//                stringsList.add(s);
//            }
//        }
//        System.out.println("Список строк изначальный = " + stringsList);
//        stringsList.add(index, item);
//        System.out.println("Добавили элемент " + item + " по индексу " + index);
//        System.out.println("Список строк полученный = " + stringsList);
//        stringsList.toArray(strings);
//        stringsList.clear();
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        strings[index] = item;
        return item;
//        stringsList.addAll(Arrays.asList(strings));
//        System.out.println("Список строк изначальный = " + stringsList);
//        String deletedItem = stringsList.set(index, item);
//        System.out.println("Добавили элемент " + item + " на позицию " + index + ", удалив старый элемент.");
//        System.out.println("Удалённый элемент = " + deletedItem);
//        System.out.println("Список строк полученный = " + stringsList);
//        stringsList.toArray(strings);
//        stringsList.clear();
//        return null;
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
//        stringsList.addAll(Arrays.asList(strings));
//        System.out.println("Список строк изначальный = " + stringsList);
//        boolean remove = stringsList.remove(item);
//        if (remove) {
//            System.out.println("Удалённый элемент = " + item);
//        } else {
//            System.out.println(false);
//        }
//        System.out.println("Список строк полученный = " + stringsList);
//        stringsList.toArray(strings);
//        stringsList.clear();
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
//        stringsList.addAll(Arrays.asList(strings));
//        System.out.println("Список строк изначальный = " + stringsList);
//        boolean remove = stringsList.remove(item);
//        if (remove) {
//            System.out.println("Удалённый элемент = " + item);
//        } else {
//            System.out.println(false);
//        }
//        System.out.println("Список строк полученный = " + stringsList);
//        stringsList.toArray(strings);
//        stringsList.clear();
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
//        for (String s : strings) {
//            if (s != null) {
//                stringsList.add(s);
//            }
//        }
//        System.out.println("Список строк изначальный = " + stringsList);
//        System.out.println("Проверка наличия элемента " + item);
//        boolean b = stringsList.contains(item);
//        stringsList.clear();
//        return b;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
//        stringsList.addAll(Arrays.asList(strings));
//        System.out.println("Список строк изначальный = " + stringsList);
//        int i = stringsList.indexOf(item);
//        stringsList.clear();
//        return i;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {

            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
//        stringsList.addAll(Arrays.asList(strings));
//        System.out.println("Список строк изначальный = " + stringsList);
//        int i = stringsList.lastIndexOf(item);
//        stringsList.clear();
//        return i;
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

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StringListImpl that = (StringListImpl) o;
//        return stringsList.equals(that.stringsList);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(stringsList);
//    }

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

