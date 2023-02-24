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
        long startTime = System.currentTimeMillis();
        initSongList(username, apiKey);
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Time to initialize song list: " + endTime + "ms");
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main("hendricksonnl", "1368311943022");
        main.songs.clear();
        main.songs.add(new MySong("Example Artist", "Example Song 1", "Example Album 1", "", ""));
        main.songs.add(new MySong("Example Artist", "Example Song 2", "Example Album 2", "", ""));
        main.songs.add(new MySong("Example Artist 2", "Example Song 3", "Example Album 3", "", ""));
        main.songs.add(new MySong("Example Artist 2", "Example Song 4", "Example Album 4", "", ""));
        main.songs.add(new MySong("Example Artist", "Example Song 5", "Example Album 5", "", ""));
        main.songs.add(new MySong("Example Artist", "Example Song 6", "Example Album 6", "", ""));
        main.songs.add(new MySong("Example Artist 2", "Example Song 7", "Example Album 7", "", ""));
        main.songs.add(new MySong("Example Artist", "Example Song 8", "Example Album 8", "", ""));

        System.out.println(main.songs.getSongsByArtist("Lin-Manuel Miranda"));
        System.out.println(main.songs.getSongsByArtist("Example Artist"));
//        System.out.println(main.songs.getSongsByArtist("kjdfkjsfs"));
//        main.songs.clear();
//        System.out.println(main.songs.getSongsByArtist("Lin-Manuel Miranda"));
    }

    private void initSongList(String username, String apiKey) throws IOException {
        Bridges bridges = new Bridges(3, username, apiKey);
        DataSource dataSource = bridges.getDataSource();

        for (Song song : dataSource.getSongData()) {
            songs.add(new MySong(song));
        }
    }
}
