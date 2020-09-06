package service;

import domain.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class MovieFileManager {

    private static int cptLine = 0;

    public MovieFileManager() {
    }

    public List<Movie> loadData(String movieFileName) throws Exception {

        Instant start = Instant.now();
        ArrayList<Movie> movies = new ArrayList<Movie>();
        String strAllFile = new String(Files.readAllBytes(Paths.get(movieFileName)));
        String[] lines = strAllFile.split("\n");

        for (String line : Arrays.stream(lines).skip(1).collect(Collectors.toList())) {
            showDuration(start);
            movies.add(splitLineToMovie(line));
        }

        System.out.println("Time elapsed for loading " + movies.size() + " movies : " + Duration.between(start, Instant.now()).toMillis() + " ms");
        return movies;
    }

    private static void showDuration(Instant start) {
        cptLine++;
        if (cptLine % 10000 == 0) {
            Instant current = Instant.now();
            long timeElapsed = Duration.between(start, current).toMillis();
            System.out.println("Ligne = " + cptLine + " in " + timeElapsed + " msec.");
        }
    }

    private Movie splitLineToMovie(String ligne) throws Exception {
        String[] colonne = ligne.split("\t");
        if (colonne == null || colonne.length != 6) {
            throw new Exception("Import Error: Wrong number of field on line: " + ligne);
        }

        Movie movie = new Movie();
        movie.id = colonne[0];
        movie.year = Integer.parseInt(colonne[1]);
        movie.length = Integer.parseInt(colonne[2]);
        movie.genres = splitColumn(colonne[3]);
        movie.directors = splitColumn(colonne[4]);
        movie.actors = splitColumn(colonne[5]);

        return movie;
    }

    private Set<String> splitColumn(String text) {
        Set<String> actors = new HashSet<>();

        String[] tabActors = text.split(",");
        for (String actor : tabActors) {
            if (!"\\N".equals(actor)) {
                actors.add(actor);
            }
        }
        return actors;
    }

    public void WriteMatchingResult(Map<String, List<String>> mapResult) {

    }
}
