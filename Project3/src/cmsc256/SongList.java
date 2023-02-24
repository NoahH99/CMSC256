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
        Pair<Integer, MySong[]> songsByArtists = getSongsByArtists(artist);
        Integer indexOfLastMatchedSong = songsByArtists.first;
        MySong[] songs = songsByArtists.second;

        bubbleSort(songs, indexOfLastMatchedSong);
        String songsAsString = getSongsAsString(songs, indexOfLastMatchedSong);

        if (songsAsString.isEmpty())
            return "There are no songs by " + artist + " in this playlist.";

        return songsAsString;
    }

    /**
     * Returns a string representation of an array of {@link cmsc256.MySong} objects.
     *
     * @param songs the array of MySong objects to be represented as a string
     * @param indexOfLastMatchedSong the index of the last matched song
     * @return a string representation of the array of MySong objects
     */
    private String getSongsAsString(MySong[] songs, Integer indexOfLastMatchedSong) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < indexOfLastMatchedSong; i++) {
            builder.append(songs[i].toString());
            if (i != indexOfLastMatchedSong - 1) builder.append("\n");
        }

        return builder.toString();
    }


    /**
     * Returns an array of MySong objects that match the given artist name.
     *
     * @param artist the name of the artist to search for in the array of MySong objects
     * @return an array of MySong objects that match the given artist name
     */
    private Pair<Integer, MySong[]> getSongsByArtists(String artist) {
        MySong[] songs = new MySong[size];
        int index = 0;

        for (MySong song : this) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                artist = song.getArtist();
                songs[index++] = song;
            }
        }

        return new Pair<>(index, songs);
    }

    /**
     * Sorts an array of MySong objects in ascending order based on their natural ordering.
     *
     * @param songs the array of MySong objects to be sorted
     * @param indexOfLastMatchedSong the index of the last matched song
     */
    private void bubbleSort(MySong[] songs, Integer indexOfLastMatchedSong) {
        for (int i = 0; i < indexOfLastMatchedSong; i++) {
            for (int j = 0; j < indexOfLastMatchedSong - 1; j++) {
                if (songs[j].compareTo(songs[j + 1]) > 0) {
                    MySong temp = songs[j];
                    songs[j] = songs[j + 1];
                    songs[j + 1] = temp;
                }
            }
        }
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

    private static class Pair<A, B> {

        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }
}
