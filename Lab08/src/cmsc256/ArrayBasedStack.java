package cmsc256;

import java.util.Arrays;

/**
 * Created by Noah Hendrickson on 3/13/23
 */
public class ArrayBasedStack<T> implements StackInterface<T> {

    private static final int INITIAL_CAPACITY = 5;

    private T[] data;
    private int topOfStack;

    public ArrayBasedStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayBasedStack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Array initial size error.");

        this.data = (T[]) new Object[capacity];
        this.topOfStack = -1;
    }

    @Override
    public void push(T newEntry) {
        if (isArrayFull()) expandArray();
        data[++topOfStack] = newEntry;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T item = data[topOfStack];
        data[topOfStack--] = null;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return data[topOfStack];
    }

    @Override
    public boolean isEmpty() {
        return topOfStack < 0;
    }

    @Override
    public void clear() {
        data = (T[]) new Object[INITIAL_CAPACITY];
        topOfStack = -1;
    }

    private void expandArray() {
        int doubledSizeOfData = data.length * 2;
        data = Arrays.copyOf(data, doubledSizeOfData);
    }

    private boolean isArrayFull() {
        return topOfStack >= data.length - 1;
    }
}
