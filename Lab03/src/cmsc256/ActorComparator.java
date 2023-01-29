package cmsc256;

import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.Comparator;

public class ActorComparator<T extends ActorMovieIMDB> implements Comparator<T> {

    @Override
    public int compare(ActorMovieIMDB o1, ActorMovieIMDB o2) {
        String[] o1Name = o1.getActor().split("_");
        String[] o2Name = o2.getActor().split("_");

        return o1Name[0].compareTo(o2Name[0]);
    }
}
