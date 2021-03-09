package Izziv2;

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}


interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";

    boolean isEmpty();
    int size();
    String toString();
}

interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
    void insert(int i, T x) throws CollectionException;
    T set(int i, T x) throws CollectionException;
    T delete(int i) throws CollectionException;
}
