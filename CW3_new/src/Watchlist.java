import java.util.*;

/**
 * Watchlist class, manages the movies a user wants to watch
 */
public class Watchlist {
    private List<String> movieIds;

    public Watchlist() {
        this.movieIds = new ArrayList<>();
    }

    public boolean addMovie(String movieId) {
        if (!movieIds.contains(movieId)) {
            movieIds.add(movieId);
            return true;
        }
        return false;
    }

    public boolean removeMovie(String movieId) {
        return movieIds.remove(movieId);
    }

    public List<String> getMovieIds() {
        return new ArrayList<>(movieIds);
    }

    public boolean isEmpty() {
        return movieIds.isEmpty();
    }
}