import java.time.LocalDate;
import java.util.*;

/**
 * Viewing history class, manages the movies a user has watched
 */
public class History {
    private List<String> movieRecords; // Format: "movieId@date"

    public History() {
        this.movieRecords = new ArrayList<>();
    }

    /**
     * Add the movie to the viewing history
     */
    public boolean addMovie(String movieId) {
        String date = LocalDate.now().toString();
        return addMovie(movieId, date);
    }

    /**
     * Add the movie to the viewing history
     */
    public boolean addMovie(String movieId, String date) {
        String record = movieId + "@" + date;
        // Allow multiple viewings of the same movie—add it as long as the viewing times are different.
        //  Check if an identical record (same movie and same time) already exists.
        if (!movieRecords.contains(record)) {
            movieRecords.add(record);
            return true;
        }
        return false;
    }

    /**
     *  Get all watched movie IDs (may contain duplicates as same movie can be watched multiple times)
     */
    public List<String> getMovieIds() {
        List<String> ids = new ArrayList<>();
        for (String record : movieRecords) {
            String[] parts = record.split("@");
            if (parts.length > 0) {
                ids.add(parts[0].trim());
            }
        }
        return ids;
    }

    /**
     * Get all viewing records (including time)
     */
    public List<String> getMovieRecords() {
        return new ArrayList<>(movieRecords);
    }

    /**
     * Get deduplicated movie ID list (each movie appears only once)
     */
    public Set<String> getUniqueMovieIds() {
        Set<String> uniqueIds = new HashSet<>();
        for (String record : movieRecords) {
            String[] parts = record.split("@");
            if (parts.length > 0) {
                uniqueIds.add(parts[0].trim());
            }
        }
        return uniqueIds;
    }

    public boolean isEmpty() {
        return movieRecords.isEmpty();
    }
}