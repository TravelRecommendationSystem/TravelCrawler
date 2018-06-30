package Utils;

/**
 * @author Umino
 * @date 7/1/2018
 */
public class Pair<U, V> {
    private U first;
    private V second;

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}
