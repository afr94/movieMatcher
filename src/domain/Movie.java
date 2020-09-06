package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Movie {
    public String id;
    public Integer year;
    public Integer length;

    public Set<String> genres;
    public Set<String> directors;
    public Set<String> actors;

    public Movie() {

    }

    public Movie(String id, int year, int length, Set<String> genres, Set<String> directors, Set<String> actors) {
        this.id = id;
        this.year = year;
        this.length = length;
        this.genres = genres;
        this.directors = directors;
        this.actors = actors;
    }


    public Movie(String id, int year, int length, List<String> genres, List<String> directors, List<String> actors) {
        Set<String> setGenres = new HashSet<>();
        setGenres.addAll(genres);
        Set<String> setDirectors = new HashSet<>();
        setDirectors.addAll(directors);
        Set<String> setActors = new HashSet<>();
        setActors.addAll(actors);

        this.id = id;
        this.year = year;
        this.length = length;
        this.genres = setGenres;
        this.directors = setDirectors;
        this.actors = setActors;
    }
}
