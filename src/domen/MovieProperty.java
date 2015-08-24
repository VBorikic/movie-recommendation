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
public class MovieProperty {

    private String name;
    private int[][] dataMatrix;
//    private double[] similarityIndexes;
//    private SimilarityValue[] similarities;
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

//    public double[] getSimmilarityIndexes() {
//        return similarityIndexes;
//    }
//
//    public void setSimmilarityIndexes(double[] simmilarityIndexes) {
//        this.similarityIndexes = simmilarityIndexes;
//    }
//    public SimilarityValue[] getSimilarities() {
//        return similarities;
//    }
//
//    public void setSimilarities(SimilarityValue[] similarities) {
//        this.similarities = similarities;
//    }
    public List<SimilarityMovieValuePair> getSimilarities() {
        return similarities;
    }

    public void setSimilarities(List<SimilarityMovieValuePair> similarities) {
        this.similarities = similarities;
    }

}
