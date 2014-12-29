package wiki.tagger.util;

/**
 * Created by vishnupk on 26/12/14.
 * Utility class to handle tuples
 */
public class Tuple<K, V> {
    private final K first;
    private final V second;

    public Tuple(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
