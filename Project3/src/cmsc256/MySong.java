package cmsc256;

import bridges.data_src_dependent.Song;

/**
 * Created by Noah Hendrickson on 2/18/23
 *
 * The MySong class represents a song, and holds information about its title, artist, album, release date, and lyrics.
 * The class implements the Comparable interface to allow for sorting of MySong objects by song title.
 */
public class MySong implements Comparable<MySong> {

    private String title;
    private String artist;
    private String album;
    private String releaseDate;
    private String lyrics;

    public MySong() {
        super();
    }

    public MySong(String title) {
        this.title = title;
    }

    public MySong(String title, String artist, String album, String releaseDate, String lyrics) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.lyrics = lyrics;
    }

    public MySong(Song song) {
        this.title = song.getSongTitle();
        this.artist = song.getArtist();
        this.album = song.getAlbumTitle();
        this.releaseDate = song.getReleaseDate();
        this.lyrics = song.getLyrics();
    }

    @Override
    public int compareTo(MySong o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return "Title: " + title + "   Album: " + album + "\n";
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getLyrics() {
        return lyrics;
    }
}
