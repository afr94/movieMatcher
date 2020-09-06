package domain;

import java.util.*;

public class CorpusData {

    public static Set<Genre> allGenres= new HashSet<>();
    public static Set<Director> allDirectors= new HashSet<>();
    public static Set<Actor> allActors= new HashSet<>();

    public CorpusData(){
    }
    
    public static Genre addGenre(String genre){
        Optional<Genre> movieGenreOpt = allGenres.parallelStream().filter(g-> g.genre.equalsIgnoreCase(genre)).findFirst();
        if(movieGenreOpt.isPresent()){
            return movieGenreOpt.get();
        }
        else{
            Genre movieGenre = new Genre(genre);
            allGenres.add(movieGenre);
            return movieGenre;
        }
    }

    public static Actor addActor(String actor){

        Optional<Actor> movieActorOpt = allActors.parallelStream().filter(a-> a.actor.equalsIgnoreCase(actor)).findFirst();
        if(movieActorOpt.isPresent()){
            return movieActorOpt.get();
        }
        else{
            Actor movieActor = new Actor(actor);
            //allActors.add(movieActor);
            return movieActor;
        }
    }

    public static Director addDirector(String director){
        Optional<Director> movieDirectorOpt = allDirectors.parallelStream().filter(a-> a.director.equalsIgnoreCase(director)).findFirst();
        if(movieDirectorOpt.isPresent()){
            return movieDirectorOpt.get();
        }
        else{
            Director movieDirector = new Director(director);
            allDirectors.add(movieDirector);
            return movieDirector;
        }
    }
}
