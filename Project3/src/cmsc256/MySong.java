package cmsc256;

import bridges.data_src_dependent.Song;

/**
 * Created by Noah Hendrickson on 2/18/23
 * <p>
 * The MySong class is a wrapper for the {@link bridges.data_src_dependent.Song} class.
 * The class implements the {@link java.lang.Comparable} interface to allow for sorting of {@link cmsc256.MySong}
 * objects by song title.
 */
public class MySong extends Song implements Comparable<MySong> {

    public MySong() {
        super();
    }

    public MySong(String artist, String song, String album, String lyrics, String release_date) {
        super(artist, song, album, lyrics, release_date);
    }

    public MySong(Song song) {
        super(song.getArtist(), song.getSongTitle(), song.getAlbumTitle(), song.getLyrics(), song.getReleaseDate());
    }

    @Override
    public int compareTo(MySong o) {
        return super.getSongTitle().compareTo(o.getSongTitle());
    }

    @Override
    public String toString() {
        return "Title: " + super.getSongTitle() + " Album: " + super.getAlbumTitle();
    }
}
