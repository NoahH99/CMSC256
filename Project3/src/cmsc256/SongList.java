package cmsc256;

import bridges.base.SLelement;

import java.util.Iterator;

/**
 * Created by Noah Hendrickson on 2/18/23
 *
 * SongList is a class that implements the List interface for storing and managing MySong objects.
 * It uses a singly linked list as the underlying data structure and provides methods for inserting,
 * removing, and accessing elements, as well as for checking if an element is contained in the list.
 * The class also implements the Iterable interface to enable iteration over the elements of the list.
 */
public class SongList implements List<MySong>, Iterable<MySong> {

    private SLelement<MySong> head;
    private int size;

    public SongList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean insert(MySong it, int position) {
        if (position < 0 || position > size) throw new IllegalArgumentException();

        if (position == 0) {
            head = new SLelement<>(it, head);
        } else {
            SLelement<MySong> current = head;

            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }

            current.setNext(new SLelement<>(it, current.getNext()));
        }

        size++;
        return true;
    }

    @Override
    public boolean add(MySong it) {
        return insert(it, size);
    }

    @Override
    public MySong remove(int position) {
        if (position < 0 || position >= size) throw new IllegalArgumentException();

        MySong removed;

        if (position == 0) {
            removed = head.getValue();
            head = head.getNext();
        } else {
            SLelement<MySong> current = head;

            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }

            removed = current.getNext().getValue();
            current.setNext(current.getNext().getNext());
        }

        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(MySong target) {
        SLelement<MySong> current = head;

        while (current != null) {
            if (current.getValue().equals(target)) return true;
            current = current.getNext();
        }

        return false;
    }

    @Override
    public MySong getValue(int position) {
        if (position < 0 || position >= size) throw new IllegalArgumentException();

        SLelement<MySong> current = head;

        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }

        return current.getValue();
    }

    @Override
    public Iterator<MySong> iterator() {
        return new SongListIterator();
    }

    public void sortOnTitle() {
        if (size < 2) return;

        SLelement<MySong> current = head;
        SLelement<MySong> next = head.getNext();

        while (next != null) {
            if (current.getValue().getTitle().compareTo(next.getValue().getTitle()) > 0) {
                MySong temp = current.getValue();

                current.setValue(next.getValue());
                next.setValue(temp);

                current = head;
                next = head.getNext();
            } else {
                current = current.getNext();
                next = next.getNext();
            }
        }
    }

    private class SongListIterator implements Iterator<MySong> {

        private SLelement<MySong> current;

        public SongListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public MySong next() {
            MySong next = current.getValue();
            current = current.getNext();
            return next;
        }
    }
}
