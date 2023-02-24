package cmsc256;

import bridges.base.SLelement;

import java.util.Iterator;

/**
 * Created by Noah Hendrickson on 2/18/23
 * <p>
 * {@link cmsc256.SongList} is a class that implements the {@link cmsc256.List} interface for storing and
 * managing {@link cmsc256.MySong} objects. It uses a singly linked list as the underlying data structure and
 * provides methods for inserting, removing, and accessing elements, as well as
 * checking if an element is contained in the list. The class also implements the {@link java.lang.Iterable}
 * interface to enable iteration over the elements of the list.
 */
public class SongList implements List<MySong>, Iterable<MySong> {

    private String playlistName;
    private SLelement<MySong> head;
    private int size;

    public SongList(String playlistName) {
        this.playlistName = playlistName;
        this.head = null;
        this.size = 0;
    }

    public SongList() {
        this.playlistName = "";
        this.head = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        this.playlistName = "";
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

    /**
     * Returns a formatted string of all songs by a specific artist in this playlist.
     * The songs are sorted alphabetically by title.
     * <p>
     *
     * @param artist the name of the artist to search for
     * @return a formatted string of all songs by a specific artist in this playlist, sorted alphabetically by title
     */
    public String getSongsByArtist(String artist) {
        StringBuilder builder = new StringBuilder();

        MySong[] songs = new MySong[size];
        int index = 0;
        for (MySong song : this) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                artist = song.getArtist();
                songs[index++] = song;
            }
        }

        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index - 1; j++) {
                if (songs[j].compareTo(songs[j + 1]) > 0) {
                    MySong temp = songs[j];
                    songs[j] = songs[j + 1];
                    songs[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < index; i++) {
            builder.append(songs[i].toString());
            if (i != index - 1) builder.append("\n");
        }

        if (builder.length() == 0) {
            builder.append("There are no songs by ").append(artist).append(" in this playlist.");
            return builder.toString();
        }

        return "Songs by " + artist + ":\n" + builder;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
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
