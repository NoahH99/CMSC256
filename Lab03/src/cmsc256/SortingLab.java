package cmsc256;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Noah Hendrickson on 01/28/2023
 */
public class SortingLab {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Bridges API access not supplied!");
            return;
        }

        List<ActorMovieIMDB> movieData = initBridgesAPI(args[0], args[1]);

        if (movieData == null) {
            System.err.println("No movie data was found.");
            return;
        }

        ActorMovieIMDB entry;
        for (int i = 0; i < 5; i++) {
            entry = movieData.get(i);
            System.out.println(i + ".  " + entry.getActor() + " was in " + entry.getMovie());
        }

        List<ActorMovieIMDB> filteredMovieData = filterMovieData(movieData, data -> data.getMovie().equals("Being_John_Malkovich_(1999)"));
        filteredMovieData.sort(new ActorComparator<>());

        for (ActorMovieIMDB data : filteredMovieData) {
            System.out.println(data.getActor());
        }
    }

    private static <T extends ActorMovieIMDB> List<T> filterMovieData(Collection<T> movieData, Predicate<T> filter) {
        List<T> filtered = new ArrayList<>();

        for (T movie : movieData) {
            if (filter.test(movie)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }

    private static List<ActorMovieIMDB> initBridgesAPI(String username, String applId) {
        Bridges bridges = new Bridges(3, username, applId);
        DataSource dataSource = bridges.getDataSource();
        List<ActorMovieIMDB> movieData = null;

        try {
            movieData = dataSource.getActorMovieIMDBData(Integer.MAX_VALUE);
        } catch (IOException e) {
            System.err.println("Unable to connect to Bridges.");
        }

        return movieData;
    }
}
