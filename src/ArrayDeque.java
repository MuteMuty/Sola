class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] a;
    private int front, back, size;

    public ArrayDeque(){
        a = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean isFull() {
        return (size == DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (size() > 0)
            sb.append(a[front].toString());
        for (int i = 0; i < size() - 1; i++)
            sb.append(", " + a[next(front + i)].toString());
        sb.append("]");
        return sb.toString();
    }

    private int next(int i){
        return (i + 1) % DEFAULT_CAPACITY;
    }

    private int prev(int i){
        return (DEFAULT_CAPACITY + i - 1) % DEFAULT_CAPACITY;
    }

    private int index(int i){
        return (front + i) % DEFAULT_CAPACITY;
    }

    // Stack
    public T top() throws CollectionException{
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return a[prev(back)];
    }

    public void push(T x) throws CollectionException{
        if (isFull())
            throw new CollectionException(ERR_MSG_FULL);
        a[back] = x;
        back = next(back);
        size++;
    }

    public T pop() throws CollectionException{
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        back = prev(back);
        T o = a[back];
        a[back] = null;
        size--;
        return o;
    }

    // Deque
    public T front() throws CollectionException{
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return a[front];
    }

    public T back() throws CollectionException{
        return top();
    }

    public void enqueue(T x) throws CollectionException{
        push(x);
    }

    public void enqueueFront(T x) throws CollectionException{
        if (isFull())
            throw new CollectionException(ERR_MSG_FULL);
        a[prev(front)] = x;
        front = prev(front);
        size++;
    }

    public T dequeue() throws CollectionException{
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        T o = a[front];
        a[front] = null;
        front = next(front);
        size--;
        return o;
    }

    public T dequeueBack() throws CollectionException{
        return pop();
    }

    // Sequence
    public T get(int i) throws CollectionException{
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (i < 0 || i >= size)
            throw new CollectionException(ERR_MSG_INDEX);
        return a[index(i)];
    }

    public void add(T x) throws CollectionException{
        push(x);
    }
}