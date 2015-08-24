/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import vsm.CosineSimilarity;
import vsm.TF_IDF;
import vsm.VSMAlgorithm;

/**
 *
 * @author Vlada
 */
public class TestVSM {

    public static void main(String[] args) {

        CosineSimilarity cs = new CosineSimilarity();
        TF_IDF t = new TF_IDF();
        VSMAlgorithm vsm = new VSMAlgorithm();

        int[][] tfTestInt = {
            {0, 0, 1, 0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1, 1, 0, 1},
            {1, 1, 1, 0, 1, 1, 0, 0},
            {1, 1, 0, 1, 1, 1, 0, 1}
        };
        double[][] tfTestDbl = {
            {2, 2, 1, 0},
            {1, 1, 2, 0.5},
            {0, 1, 1, 3}
        };

//        double[] nizA = new double[]{0.5,1.0};
//        double[] nizB = new double[]{1.0,0.5};
//        
//        System.out.println("slicnost: "+cs.calculateCosineSimilarity(nizA, nizB)); 
//        t.calculateTFIDF(tfTest);
//        t.normalizeMatrixRows(tfTestDbl);
        double[] slicnosti = vsm.calculateObjectSimalarities(tfTestInt, 1);
        System.out.println("Poredjenje filma 2 sa ostalim filmovima");
        //ocekivani rezultat je [ 0 film 1 je potpuno razlicit od filma dva ( nijedan isti property)
        //                        1 film 2 se poredi sa samin sobom
        //                        0<x<1  film 3 je donekle slican sa filmom 2
        //                        1 film 4 je potpuno isti kao film 2 (imaju potpuno iste property-je) 
        //                           ]
        for (int i = 0; i < slicnosti.length; i++) {
            System.out.println("film " + (i+1) + " i film 2 = " + slicnosti[i]);
        }
    }
}
