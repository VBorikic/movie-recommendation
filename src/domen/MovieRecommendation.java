/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vlada.borikic
 */
public class MovieRecommendation implements Serializable{
    private String movie;
    private List<String> movieSugestions;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public List<String> getMovieSugestions() {
        return movieSugestions;
    }

    public void setMovieSugestions(List<String> movieSugestions) {
        this.movieSugestions = movieSugestions;
    }
    
    
}
