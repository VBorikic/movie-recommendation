/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import com.hp.hpl.jena.rdf.model.Resource;
import java.util.List;

/**
 *
 * @author vlada.borikic
 */
public class MovieRecommendation {
    private Resource movie;
    private List<Resource> movieSugestions;

    public Resource getMovie() {
        return movie;
    }

    public void setMovie(Resource movie) {
        this.movie = movie;
    }

    public List<Resource> getMovieSugestions() {
        return movieSugestions;
    }

    public void setMovieSugestions(List<Resource> movieSugestions) {
        this.movieSugestions = movieSugestions;
    }
    
    
}
