/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import com.hp.hpl.jena.rdf.model.Resource;
import domen.MovieProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlada
 */
public class Session {

    private static Session instance;
    private List<Resource> movies;
    private List<MovieProperty> movieProperties;

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
        if (movieProperties == null) {
            movieProperties = new ArrayList<>();
        }
        movieProperties.add(mp);
    }

}
