/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlada
 */
public class Result {

    private List<SimilarityValue> finalSimilarities;

    public Result() {
        finalSimilarities = new ArrayList<>();
    }

    public List<SimilarityValue> getSimilarities() {
        return finalSimilarities;
    }

    public void setSimilarities(List<SimilarityValue> finalSimilarities) {
        this.finalSimilarities = finalSimilarities;
    }

}
