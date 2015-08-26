/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlada
 */
public class Result implements Serializable{

    private List<SimilarityMovieValuePair> finalSimilarities;

    public Result() {
        finalSimilarities = new ArrayList<>();
    }

    public List<SimilarityMovieValuePair> getSimilarities() {
        return finalSimilarities;
    }

    public void setSimilarities(List<SimilarityMovieValuePair> finalSimilarities) {
        this.finalSimilarities = finalSimilarities;
    }

}
