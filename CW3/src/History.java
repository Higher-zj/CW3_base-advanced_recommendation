import java.util.*;

/**
 * Viewing history class, manages the movies a user has watched
 */
public class History {
    private List<String> movieIds;

    public History() {
        this.movieIds = new ArrayList<>();
    }

    public boolean addMovie(String movieId) {
        if (!movieIds.contains(movieId)) {
            movieIds.add(movieId);
            return true;
        }
        return false;
    }

    public List<String> getMovieIds() {
        return new ArrayList<>(movieIds);
    }

    public boolean isEmpty() {
        return movieIds.isEmpty();
    }
}