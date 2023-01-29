package cmsc256;

import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.Comparator;

/**
 * Created by Noah Hendrickson on 01/28/2023
 * <br>
 * The ActorComparator class compares ActorMovieIMDB objects based on the first
 * name of the actor.
 * <p>
 * <b>Note:</b> The type of ActorMovieIMDB objects being compared must extend ActorMovieIMDB.
 * </p>
 *
 * @param <T> the type of ActorMovieIMDB objects being compared, must extend ActorMovieIMDB
 */
public class ActorComparator<T extends ActorMovieIMDB> implements Comparator<T> {

    /**
     * Compares two ActorMovieIMDB objects based on the first name of the actor.
     *
     * @param o1 the first ActorMovieIMDB object
     * @param o2 the second ActorMovieIMDB object
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to,
     * or greater than the second
     */
    @Override
    public int compare(T o1, T o2) {
        String[] o1Name = o1.getActor().split("_");
        String[] o2Name = o2.getActor().split("_");

        return o1Name[0].compareTo(o2Name[0]);
    }
}
