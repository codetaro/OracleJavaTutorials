package generics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Restrictions {

    public static void main(String[] args) throws Exception {

        /* Cannot instantiate generic types with primitive types */
        // Pair<int, char> p = new Pair<>(8,'a');  // compile-time error
        MyPair<Integer, Character> p = new MyPair<>(8, 'a');

        /* Cannot create instances of type parameters */
        List<String> ls = new ArrayList<>();
        append(ls, String.class);

        /* Cannot declare static fields whose types are type parameters */
        // MobileDevice<Smartphone> phone = new MobileDevice<>();
        // MobileDevice<Pager> pager = new MobileDevice<>();
        // MobileDevice<TabletPC> pc = new MobileDevice<>();

        /* Cannot use casts or instanceof with parameterized types */
        List<Integer> li = new ArrayList<>();
        // List<Number> ln = (List<Number>) li;  // compile-time error
        List<String> l1 = new ArrayList<>();
        ArrayList<String> l2 = (ArrayList<String>) l1;

        /* Cannot create arrays of parameterized types */
        // List<Integer>[] arrayOfLists = new List<Integer>[2];  // compile-time error

        /* Cannot create, catch, or throw objects of parameterized types */

    }

    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        // E elem = new E();  // compile-time error
        E elem = cls.newInstance();
        list.add(elem);
    }

    public static void rtti(List<?> list) {
        // if (list instanceof ArrayList<Integer>) {  // compile-time error
        if (list instanceof ArrayList<?>) {  // compile-time error

        }
    }

    public static <T extends Exception, J> void execute(List<J> jobs) {
        try {
            for (J job : jobs) {
                // ...
            }
            // } catch (T e) {  // compile-time error
        } catch (Exception e) {  // compile-time error
            // ...
        }
    }
}

class MyPair<K, V> {

    private K key;
    private V value;

    public MyPair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class MobileDevice<T> {

    // private static T os;  // compile-time error
}

// extends Throwable indirectly
// class MathException<T> extends Exception {}

// extends Throwable directly
// class QueueFullException<T> extends Throwable {}

class Parser<T extends Exception> {

    public void parse(File file) throws T {
        // ...
    }
}

class Example {
    public void print(Set<String> strSet) {
    }
//  public void print(Set<Integer> intSet) {}
}
