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
 * <br>
 * <p>
 * The SortingLab class is the main class that runs the program.
 * It is responsible for initializing the Bridges API, filtering and sorting
 * the movie data, and printing out the results.
 * </p>
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

    /**
     * Filters a collection of movie data based on a given predicate.
     *
     * <p>
     * <b>Note:</b> The type of movie data being filtered must extend ActorMovieIMDB.
     * </p>
     *
     * @param <T> the type of movie data being filtered, must extend ActorMovieIMDB
     * @param movieData the collection of movie data to be filtered
     * @param filter the predicate used to filter the movie data
     * @return a list of filtered movie data
     */
    private static <T extends ActorMovieIMDB> List<T> filterMovieData(Collection<T> movieData, Predicate<T> filter) {
        List<T> filtered = new ArrayList<>();

        for (T movie : movieData) {
            if (filter.test(movie)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }

    /**
     * Initializes the Bridges API with the given username and application ID.
     *
     * @param username the Bridges API username
     * @param applicationId the Bridges API application ID
     * @return the movie data retrieved from the Bridges API
     */
    private static List<ActorMovieIMDB> initBridgesAPI(String username, String applicationId) {
        Bridges bridges = new Bridges(3, username, applicationId);
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
