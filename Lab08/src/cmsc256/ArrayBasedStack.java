package cmsc256;

import java.util.Arrays;

/**
 * Created by Noah Hendrickson on 3/13/23
 */
public class ArrayBasedStack<T> implements StackInterface<T> {

    private static final int INITIAL_CAPACITY = 5;

    private T[] data;
    private int indexOfTopOfStack;

    public ArrayBasedStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayBasedStack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Array initial size error.");

        this.data = (T[]) new Object[capacity];
        this.indexOfTopOfStack = -1;
    }

    @Override
    public void push(T newEntry) {
        if (isArrayFull()) expandArray();
        data[++indexOfTopOfStack] = newEntry;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T item = data[indexOfTopOfStack];
        data[indexOfTopOfStack--] = null;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return data[indexOfTopOfStack];
    }

    @Override
    public boolean isEmpty() {
        return indexOfTopOfStack < 0;
    }

    @Override
    public void clear() {
        this.data = (T[]) new Object[INITIAL_CAPACITY];
        this.indexOfTopOfStack = -1;
    }

    private void expandArray() {
        int doubledSizeOfData = data.length * 2;
        data = Arrays.copyOf(data, doubledSizeOfData);
    }

    private boolean isArrayFull() {
        return indexOfTopOfStack >= data.length - 1;
    }
}
