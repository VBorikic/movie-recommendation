/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import vsm.CosineSimilarity;

/**
 *
 * @author Vlada
 */
public class TestVSM {
    public static void main(String[] args) {
        
        CosineSimilarity cs = new CosineSimilarity();
        
        double[] nizA = new double[]{0.5,1.0};
        double[] nizB = new double[]{1.0,0.5};
        
        System.out.println("slicnost: "+cs.getCosineSimilarity(nizA, nizB)); 
    }
}
