package generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Questions {

    public static void main(String[] args) {
        Collection<Integer> ci = Arrays.asList(1, 2, 3, 4);
        int count = Answer1.countIf(ci, new OddPredicate());
        System.out.println("Number of odd integers = " + count);
    }
}

// #1. Write a generic method to count the number of elements in a collection that have a specific property.
class Answer1 {
    public static <T> int countIf(Collection<T> c, UnaryPredicate<T> p) {
        int cnt = 0;
        for (T elem : c) {
            if (p.test(elem)) {
                cnt++;
            }
        }
        return cnt;
    }
}

interface UnaryPredicate<T> {
    public boolean test(T obj);
}

class OddPredicate implements UnaryPredicate<Integer> {
    @Override
    public boolean test(Integer i) {
        return i % 2 != 0;
    }
}

// #2. Will the following class compile?
//* No. > operator applies only to primitive numeric types.

// #3. Write a generic method to exchange the positions of two different elements in an array.
class Answer2 {
    public <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

// #4. If the compiler erases all type parameters at compile time, why should you use generics?
//* Java compiler enforces tighter type checks on generic code at compile time.
//* Generics support programming types as parameters.
//* Generics enable you to implement generic algorithms.

// #5. What is the following class converted to after type erasure?
class Pair3 {

    public Pair3(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    private Object key;
    private Object value;
}

// #6. What is the following method converted to after type erasure?
class Answer6 {
    public static int findFirstGreaterThan(Comparable[] at, Comparable elem) {
        return -1;
    }
}

// #7. Will the following method compile? If not, why?
// Yes

// #8. Write a generic method to find the maximal element in the range [begin, end) of a list.
final class Solution8 {
    public static <T extends Object & Comparable<? super T>>
    T max(List<? extends T> list, int begin, int end) {
        T maxElem = list.get(begin);
        for (++begin; begin < end; ++begin)
            if (maxElem.compareTo(list.get(begin)) < 0)
                maxElem = list.get(begin);
        return maxElem;
    }
}

// #9. Will the following class compile? If not, why?
// No, static field cannot be generic types

// #10. Will the following code compile? If not, why?
// No, Node<Circle> is not a subtype of Node<Shape>

// #11. Will the following code compile? If not, why?
// Yes

// #12. How do you invoke the following method to find the first integer in a list that is relatively prime to a list of specified integers?
final class Algorithm12 {

    public static <T>
    int findFirst(List<T> list, int begin, int end, UnaryPredicate<T> p) {

        for (; begin < end; ++begin)
            if (p.test(list.get(begin)))
                return begin;
        return -1;
    }

    // x > 0 and y > 0
    public static int gcd(int x, int y) {
        for (int r; (r = x % y) != 0; x = y, y = r) {
        }
        return y;
    }
}

class RelativePrimePredicate implements UnaryPredicate<Integer> {
    public RelativePrimePredicate(Collection<Integer> c) {
        this.c = c;
    }

    @Override
    public boolean test(Integer x) {
        for (Integer i : c)
            if (Algorithm12.gcd(x, i) != 1)
                return false;
        return c.size() > 0;
    }

    private Collection<Integer> c;
}

class Solution12 {

    public static void main(String[] args) {

        List<Integer> li = Arrays.asList(3, 4, 6, 8, 11, 15, 28, 32);
        Collection<Integer> c = Arrays.asList(7, 18, 19, 25);
        UnaryPredicate<Integer> p = new RelativePrimePredicate(c);

        int i = Algorithm12.findFirst(li, 0, li.size(), p);

        if (i != -1) {
            System.out.print(li.get(i) + " is relatively prime to ");
            for (Integer k : c)
                System.out.print(k + " ");
            System.out.println();
        }
    }
}