/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import com.hp.hpl.jena.rdf.model.Resource;

/**
 *
 * @author vlada.borikic
 */
public class SimilarityMovieValuePair {
    private Resource movie;
    private double similarity;

    public Resource getMovie() {
        return movie;
    }

    public void setMovie(Resource movie) {
        this.movie = movie;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    
    
}
