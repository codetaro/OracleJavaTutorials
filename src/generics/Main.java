package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("hello");
        String s = list.get(0);  // no cast

        Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);
        Pair<String, String> p2 = new OrderedPair<>("hello", "world");
        OrderedPair<String, Box<Integer>> p = new OrderedPair<>("primes", new Box<Integer>());

        Pair<Integer, String> p3 = new OrderedPair<>(1, "apple");
        Pair<Integer, String> p4 = new OrderedPair<>(2, "pear");
        boolean same = Util.compare(p3, p4);

        Box<Integer> integerBox = new Box<Integer>();
        integerBox.set(new Integer(10));

        List<Integer> li = Arrays.asList(1, 2, 3);
        System.out.println("sum = " + sumOfList(li));

        List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
        System.out.println("sum = " + sumOfList(ld));

        List<String> ls = Arrays.asList("one", "two", "three");
        printList(li);
        printList(ls);

    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }

    public static double sumOfList(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list)
            s += n.doubleValue();
        return s;
    }

    public static void printList(List<?> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    // Counts the number of occurrences of elem in anArray
    public static <T> int count(T[] anArray, T elem) {
        int cnt = 0;
        for (T e : anArray)
            if (e.equals(elem))
                ++cnt;
        return cnt;
    }
}
