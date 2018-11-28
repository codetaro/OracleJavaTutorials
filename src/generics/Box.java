package generics;

/**
 * Generic version of the Box class.
 *
 * @param <T> the type of the value being boxed
 */
public class Box<T> {

    // T stands for "Type"
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }
}
