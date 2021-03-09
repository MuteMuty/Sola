package Izziv2;

public class LinkedSequence<T> implements Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] a;

    class Node {
        T value;
        Node next, prev;
    }
    private Node first;
    private int size;

    public LinkedSequence(){
        a = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    private Node index(int i){
        Node n = first;
        while (i > 0){
            n = n.next;
            i--;
        }
        return n;
    }

    public T get(int i) throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (i < 0 || i >= size)
            throw new CollectionException(ERR_MSG_INDEX);
        return index(i).value;
    }

    public void add(T x) throws CollectionException {
        Node n = new Node();
        n.value = x;
        if (isEmpty()){
            first = n;
        }else {
            n.prev = index(size - 1);
            index(size - 1).next = n;
        }
        a[size] = (T) n;
        size++;
    }

    public void insert(int i, T x) throws CollectionException {
        if (i < 0 || i > size)
            throw new CollectionException(ERR_MSG_INDEX);
        Node n = new Node();
        n.value = x;
        if (isEmpty()){
            first = n;
        }else if (i == size){
            n.prev = index(i - 1);
            index(i - 1).next = n;
        }else if (i == 0){
            n.next = index(i);
            index(i).prev = n;
            first = n;
        }else {
            n.prev = index(i - 1);
            n.next = index(i);
            index(i - 1).next = n;
            index(i).prev = n;
        }
        a[size] = (T) n;
        size++;
    }

    public T set(int i, T x) throws CollectionException {
        if (i < 0 || i >= size)
            throw new CollectionException(ERR_MSG_INDEX);
        T o = index(i).value;
        index(i).value = x;
        return o;
    }

    public T delete(int i) throws CollectionException {
        if (i < 0 || i >= size)
            throw new CollectionException(ERR_MSG_INDEX);
        Node n = index(i);
        if (i == size - 1)
            index(i - 1).next = null;
        else if (i == 0) {
            index(1).prev = null;
            first = index(1);
        }
        else {
            index(i + 1).prev = index(i - 1);
            index(i - 1).next = index(i + 1);
        }
        size--;
        return n.value;
    }

}