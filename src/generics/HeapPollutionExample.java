package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapPollutionExample {

    public static void main(String[] args) {

        List<String> stringListA = new ArrayList<String>();
        List<String> stringListB = new ArrayList<String>();

        ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
        ArrayBuilder.addToList(stringListB, "Ten", "Eleven", "Twelve");
        List<List<String>> listofStringLists =
                new ArrayList<List<String>>();
        ArrayBuilder.addToList(listofStringLists,
                stringListA, stringListB);

        ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
    }
}

class ArrayBuilder {

    @SafeVarargs
    public static <T> void addToList(List<T> listArg, T... elements) {
        for (T x : elements) {
            listArg.add(x);
        }
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public static void faultyMethod(List<String>... l) {
        Object[] objectArray = l;  // valid
        objectArray[0] = Arrays.asList(42);
        String s = l[0].get(0);    // ClassCastException thrown
    }
}
