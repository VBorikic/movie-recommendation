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
public class MovieProperty implements Serializable{

    private String name;
    private int[][] dataMatrix;
    private List<SimilarityMovieValuePair> similarities;

    public MovieProperty() {
        similarities = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][] getDataMatrix() {
        return dataMatrix;
    }

    public void setDataMatrix(int[][] dataMatrix) {
        this.dataMatrix = dataMatrix;
    }

    public List<SimilarityMovieValuePair> getSimilarities() {
        return similarities;
    }

    public void setSimilarities(List<SimilarityMovieValuePair> similarities) {
        this.similarities = similarities;
    }

}
