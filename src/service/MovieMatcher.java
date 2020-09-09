package service;

import domain.Movie;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieMatcher {

    private static int cptLine = 0;

    public void LaunchMatching(String movieFilePathName) throws Exception {
        cptLine=0;
        MovieFileManager movieFileManager = new MovieFileManager();

        List<Movie> allMovies = movieFileManager.loadData(movieFilePathName);

        Map<String, List<String>> mapResult = findMatches(allMovies);

        movieFileManager.WriteMatchingResult(mapResult);

    }

    public Map<String, List<String>> findMatches(List<Movie> _allMovies) {
        Instant start = Instant.now();
        List<Movie> allMovies = new ArrayList<>(_allMovies);
        Map<String, List<String>> mapResult = new HashMap<>();

        List<Movie> movies = new ArrayList<>(allMovies);
        for (Movie movie : allMovies) {
            if (movie.alreadyAssociated == true){
                continue;
            }
            movies.remove(0);
            List<String> listMatches = new ArrayList<>(); //70ms
            showDuration(start);
            movies.parallelStream().forEach(m -> {//
                if (checkYear(movie.year, m.year) &&
                    checkLength(movie.length, m.length)) {
                    m.alreadyAssociated= true;
                    addMatch(listMatches, m.id); //
                }
            });//
            if (listMatches.size() > 0) {
                mapResult.put(movie.id, listMatches);
            }
        }

        System.out.println("Time elapsed for matching " + allMovies.size() + " movies : " + Duration.between(start, Instant.now()).toMillis() + " ms (" + Duration.between(start, Instant.now()).toSeconds() + " s)");
        System.out.println("Number of matching movies: "+mapResult.size());
        return mapResult;
    }

    private synchronized void addMatch(List<String> listMatches, String id) {
        listMatches.add(id);
    }


    private static void showDuration(Instant start) {
        cptLine++;
        if (cptLine % 10000 == 0) {
            Instant current = Instant.now();
            long timeElapsed = Duration.between(start, current).toMillis();
            System.out.println("Ligne = " + cptLine + " in " + timeElapsed + " msec.");
        }
    }

    private boolean checkLength(Integer lengthRef, Integer lengthToCompare) {
        return ((Math.abs(lengthRef - lengthToCompare) / (double)(lengthRef + lengthToCompare)) <= 0.025);
    }

    private boolean checkYear(Integer yearMovieReference, Integer yearMovieToCompare) {
        return (yearMovieToCompare >= yearMovieReference - 1 && yearMovieToCompare <= yearMovieReference + 1);
    }
}
