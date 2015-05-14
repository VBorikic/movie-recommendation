/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import vsm.CosineSimilarity;
import vsm.TF_IDF;

/**
 *
 * @author Vlada
 */
public class TestVSM {

    public static void main(String[] args) {

        CosineSimilarity cs = new CosineSimilarity();
        TF_IDF t = new TF_IDF();
    
//        double[] nizA = new double[]{0.5,1.0};
//        double[] nizB = new double[]{1.0,0.5};
//        
//        System.out.println("slicnost: "+cs.calculateCosineSimilarity(nizA, nizB)); 
//        int[][] tfTest = {
//            {1, 1, 0, 0},
//            {1, 1, 1, 1},
//            {0, 1, 1, 0}
//        };
//        
//        t.calculateTFIDF(tfTest);
        
        double[][] tfTest = {
            {2, 2, 1, 0},
            {1, 1, 2, 0.5},
            {0, 1, 1, 3}
        };

        t.normalizeMatrixRows(tfTest);
    }
}
