/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author Vlada
 */
public class MovieProperty {

    private String name;
    private int[][] dataMatrix;
    private double[] simmilarityIndexes;

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

    public double[] getSimmilarityIndexes() {
        return simmilarityIndexes;
    }

    public void setSimmilarityIndexes(double[] simmilarityIndexes) {
        this.simmilarityIndexes = simmilarityIndexes;
    }

}
