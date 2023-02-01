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
 * </br>
 * SortingLab class is the main class that demonstrates the usage of the {@link ActorComparator} class
 * and the `filterMovieData` method.
 * <p>
 * The program takes in two command line arguments, a Bridges API username and API key, and uses them to
 * connect to the Bridges API and retrieve a list of {@link ActorMovieIMDB} objects. The program then filters
 * the list based on a specific movie title and sorts the resulting list by the actor's first name using
 * the {@link ActorComparator} class. The filtered and sorted list is then printed to the console.
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
            System.out.println(i + ". " + entry.getActor() + " was in " + entry.getMovie());
        }

        List<ActorMovieIMDB> filteredMovieData = filterMovieData(movieData, data -> data.getMovie().equals("Being_John_Malkovich_(1999)"));
        filteredMovieData.sort(new ActorComparator<>());

        for (ActorMovieIMDB data : filteredMovieData) {
            System.out.println(data.getActor());
        }
    }

    /**
     * Filters a collection of {@link ActorMovieIMDB} objects based on a given predicate and returns a new list
     * containing the filtered objects.
     *
     * @param movieData the collection of {@link ActorMovieIMDB} objects to filter
     * @param filter the predicate to use for filtering
     * @param <T> type parameter that extends {@link ActorMovieIMDB}
     * @return a new list containing the filtered {@link ActorMovieIMDB} objects
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
     * Initializes a connection to the Bridges API using the given username and API key and retrieves a list of
     * {@link ActorMovieIMDB} objects.
     *
     * @param username the Bridges API username
     * @param applicationId the Bridges API key
     * @return a list of {@link ActorMovieIMDB} objects retrieved from the Bridges API
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
