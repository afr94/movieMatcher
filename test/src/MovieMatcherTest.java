import domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MovieMatcher;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieMatcherTest {
    public static final String ID_1 = "id1";
    public static final String ID_2 = "id2";
    public static final String ID_3 = "id3";
    private MovieMatcher movieMatcher;

    @BeforeEach
    void setup() {
        movieMatcher = new MovieMatcher();
    }

    @Test
    void findMatches_OK() throws Exception {
        List<Movie> movies = createMatchingMovies();

        Map<String, List<String>> result = movieMatcher.findMatches(movies);
        assertEquals(1, result.size());
        assertEquals(ID_2, result.get(ID_1).get(0));

    }

    @Test
    void findMatches_KO_year() throws Exception {
        List<Movie> movies = createMatchingMovies();
        movies.get(1).year=(movies.get(0).year)+2;
        Map<String, List<String>> result = movieMatcher.findMatches(movies);
        assertEquals(0, result.size());
    }

    @Test
    void findMatches_KO_Length() throws Exception {
        List<Movie> movies = createMatchingMovies();
        movies.get(1).length= (int)((movies.get(0).length)*1.1);
        Map<String, List<String>> result = movieMatcher.findMatches(movies);
        assertEquals(0, result.size());

    }
    private List<Movie> createMatchingMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(ID_1, 1999, 78, Arrays.asList("genre1"), Arrays.asList("dir1"), Arrays.asList("actor1","actor2")));
        movies.add(new Movie(ID_2, 2000, 80, Arrays.asList("genre1"), Arrays.asList("dir1"), Arrays.asList("actor1","actor2")));
        return movies;
    }


}