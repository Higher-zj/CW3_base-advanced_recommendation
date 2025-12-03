import java.io.*;
import java.util.*;

/**
 * File manager class, responsible for reading and writing CSV files
 */
public class FileManager {
    private static final String MOVIE_FILE = "data/movies.csv";
    private static final String USER_FILE = "data/users.csv";

    /**
     * Load movie data from CSV file
     */
    public static Map<String, Movie> loadMovies() {
        Map<String, Movie> movies = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIE_FILE))) {
            String line;
            reader.readLine(); // Skip header line

            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        String id = parts[0].trim();
                        String title = parts[1].trim();
                        String genre = parts[2].trim();
                        int year = Integer.parseInt(parts[3].trim());
                        double rating = Double.parseDouble(parts[4].trim());

                        movies.put(id, new Movie(id, title, genre, year, rating));
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing movie line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Movie file not found: " + MOVIE_FILE);
        } catch (IOException e) {
            System.out.println("Error reading movie file: " + e.getMessage());
        }

        return movies;
    }

    /**
     * Load user data from CSV file
     */
    public static Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            reader.readLine(); // Skip header line


            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        String username = parts[0].trim();
                        String password = parts[1].trim();

                        // Parse watchlist
                        Watchlist watchlist = new Watchlist();
                        if (!parts[2].trim().isEmpty()) {
                            String[] watchlistItems = parts[2].split(";");
                            for (String item : watchlistItems) {
                                if (!item.trim().isEmpty()) {
                                    watchlist.addMovie(item.trim());
                                }
                            }
                        }

                        // Parse viewing history
                        History history = new History();
                        if (!parts[3].trim().isEmpty()) {
                            String[] historyItems = parts[3].split(";");
                            for (String item : historyItems) {
                                if (!item.trim().isEmpty()) {
                                    history.addMovie(item.trim());
                                }
                            }
                        }

                        users.put(username, new User(username, password, watchlist, history));
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing user line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found: " + USER_FILE);
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }

        return users;
    }

    /**
     * Save user data to CSV file
     */
    public static void saveUsers(Map<String, User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            writer.println("Username,Password,Watchlist,History");

            for (User user : users.values()) {
                // Build watchlist string
                String watchlistStr = "";
                List<String> watchlistIds = user.getWatchlist().getMovieIds();
                for (int i = 0; i < watchlistIds.size(); i++) {
                    if (i > 0) watchlistStr += ";";
                    watchlistStr += watchlistIds.get(i);
                }

                // Build history string
                String historyStr = "";
                List<String> historyIds = user.getHistory().getMovieIds();
                for (int i = 0; i < historyIds.size(); i++) {
                    if (i > 0) historyStr += ";";
                    historyStr += historyIds.get(i);
                }

                writer.println(user.getUsername() + "," + user.getPassword() + "," + watchlistStr + "," + historyStr);
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }


}