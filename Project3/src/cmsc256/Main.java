package cmsc256;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;

import java.io.IOException;

/**
 * Created by Noah Hendrickson on 2/18/23
 */
public class Main {

    private final SongList songs = new SongList();

    private Main(String username, String apiKey) throws IOException {
        initSongList(username, apiKey);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main(args[0], args[1]);

        System.out.println(main.getSongsByArtist("The Beatles"));
        System.out.println(main.getSongsByArtist("Queen"));
    }

    private String getSongsByArtist(String artist) {
        StringBuilder builder = new StringBuilder();

        songs.sortOnTitle();
        for (MySong song : songs) {
            if (song.getArtist().equals(artist)) {
                builder.append(song);
            }
        }

        if (builder.length() == 0) {
            builder.append("There are no songs by ").append(artist).append(" in this playlist.");
        }

        return builder.toString();
    }

    private void initSongList(String username, String apiKey) throws IOException {
        Bridges bridges = new Bridges(3, username, apiKey);
        DataSource dataSource = bridges.getDataSource();

        for (Song song : dataSource.getSongData()) {
            songs.add(new MySong(song));
        }
    }
}
