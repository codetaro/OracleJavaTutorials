package generics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Restrictions {

    public static void main(String[] args) throws Exception {

        // #1. Cannot Instantiate Generic Types with Primitive Types
        Pair2<Integer, Character> p = new Pair2<>(8, 'a');

        // #5. Cannot Create Arrays of Parameterized Types
        /*Object[] stringLists = new List<String>[2];
        stringLists[0] = new ArrayList<String>();
        stringLists[1] = new ArrayList<Integer>();*/
    }

    // #2. Cannot Create Instances of Type Parameters
    /*public static <E> void append(List<E> list) {
        E elem = new E();
        list.add(elem);
    }*/
    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();
        list.add(elem);
    }

    // #4. Cannot Use Casts or instanceof With Parameterized Types
    public static <E> void rtti(List<E> list) {
//        if (list instanceof ArrayList<Integer>) {}
    }

    public static void rtti2(List<?> list) {
        if (list instanceof ArrayList<?>) {
        }
    }

    // #6. Cannot Create, Catch, or Throw Objects of Parameterized Types
    /*public static <T extends Exception, J> void execute(List<J> jobs) {
        try {
            for (J job : jobs) {
                // ...
            }
        } catch (T e) {  // compile-time error
            // ...
        }
    }*/
}

class Pair2<K, V> {

    private K key;
    private V value;

    public Pair2(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

// #3. Cannot Declare Static Fields Whose Types are Type Parameters
class MobileDevice<T> {
//    private static T os;
}

// #6. Cannot Create, Catch, or Throw Objects of Parameterized Types
//class MathException<T> extends Exception {}       // extends Throwable indirectly
//class QueueFullException<T> extends Throwable {}  // extends Throwable directly
class Parser<T extends Exception> {

    public void parse(File file) throws T {
    }
}

// #7. Cannot Overload a Method Where the Formal Parameter Types of Each Overload Erase to the Same Raw Type
class Example {

    //    public void print(Set<String> strSet) {}
    public void print(Set<Integer> intSet) {
    }
}
