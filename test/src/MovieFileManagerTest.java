import domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MovieFileManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieFileManagerTest {
    private String RootFileDirectory ="..\\test\\";
    private MovieFileManager movieFileManager;

    @BeforeEach
    void setup() {
        movieFileManager = new MovieFileManager();
    }

    @Test
    void loadData_OK() throws Exception {
        List<Movie> movies = movieFileManager.loadData(RootFileDirectory + "movies10.tsv");
        assertEquals(3, movies.size());

        // Normal complete line
        assertEquals("9bd4cbab-0af9-43c1-a6fe-8440b5f7979a", movies.get(0).id);
        assertEquals(2015, movies.get(0).year);
        assertEquals(62, movies.get(0).length);
        assertEquals(0, movies.get(0).genres.size());
        assertEquals(1, movies.get(0).directors.size());
        assertEquals(true, movies.get(0).directors.contains("Stephen Ang"));
        assertEquals(4, movies.get(0).actors.size());
        assertEquals(true, movies.get(0).actors.contains("Stephen Ang"));
        assertEquals(true, movies.get(0).actors.contains("George Capacete"));
        assertEquals(true, movies.get(0).actors.contains("Kimberly Cashner"));
        assertEquals(true, movies.get(0).actors.contains("Peggy Glenn"));

        // for Empty values
        assertEquals("8b2789fe-36fc-4f74-9094-b11ec46bfd42", movies.get(1).id);
        assertEquals(2018, movies.get(1).year);
        assertEquals(60, movies.get(1).length);
        assertEquals(0, movies.get(1).genres.size());
        assertEquals(0, movies.get(1).directors.size());
        assertEquals(0, movies.get(1).actors.size());

        //For special characters
        assertEquals("ca1597d4-39cc-429d-a5b0-3cbf0281648b", movies.get(2).id);
        assertEquals(1966, movies.get(2).year);
        assertEquals(90, movies.get(2).length);
        assertEquals(0, movies.get(2).genres.size());
        assertEquals(1, movies.get(2).directors.size());
        assertEquals(true, movies.get(2).directors.contains("Yilmaz Atadeniz"));
        assertEquals(6, movies.get(2).actors.size());
        assertEquals(true, movies.get(2).actors.contains("Erol Günaydin"));
        assertEquals(true, movies.get(2).actors.contains("Yilmaz Güney"));
        assertEquals(true, movies.get(2).actors.contains("Müjgan Agrali"));
        assertEquals(true, movies.get(2).actors.contains("Yildirim Gencer"));
        assertEquals(true, movies.get(2).actors.contains("Ali Sen"));
        assertEquals(true, movies.get(2).actors.contains("Hüseyin Peyda"));

    }
}