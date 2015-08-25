/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import com.hp.hpl.jena.rdf.model.Resource;
import domen.MovieProperty;
import domen.MovieRecommendation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlada
 */
public class Session implements Serializable{

    private static Session instance;
    private List<Resource> movies;
    private List<MovieProperty> movieProperties;
    private List<MovieRecommendation> recommendations;

    private Session(){
        movieProperties = new ArrayList<>();
        recommendations = new ArrayList<>();
    }
    
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public List<Resource> getMovies() {
        return movies;
    }

    public void setMovies(List<Resource> movies) {
        this.movies = movies;
    }

    public List<MovieProperty> getMovieProperties() {
        return movieProperties;
    }

    public void addMovieProperty(MovieProperty mp) {
//        if (movieProperties == null) {
//            movieProperties = new ArrayList<>();
//        }
        movieProperties.add(mp);
    }

    public List<MovieRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<MovieRecommendation> recommendations) {
        this.recommendations = recommendations;
    }
    public int getMovieNumberFromList(Resource movie){
        return movies.indexOf(movie);
    }
}
