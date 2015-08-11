/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import java.io.File;
import persistence.DataModelManager;
import services.MovieDataExtractor;
import services.MovieRecommendationService;
import util.Constants;
import vsm.CosineSimilarity;
import vsm.TF_IDF;
import vsm.VSMAlgorithm;

/**
 *
 * @author Vlada
 */
public class TestAll {

    public static void main(String[] args) {
//        DataModelManager.getInstance().importData("data" + File.separator + "data.rdf", "RDF/XML");
//
//        MovieDataExtractor mde = new MovieDataExtractor();
//
//      
////        int[][] movieDataMatrix = mde.extractMovieDataFromModel(DataModelManager.getInstance().getModel(), "starring");
//        mde.extractMovieDataFromModel(DataModelManager.getInstance().getModel(), "starring");
//
//        CosineSimilarity cs = new CosineSimilarity();
//        TF_IDF t = new TF_IDF();
//        VSMAlgorithm vsm = new VSMAlgorithm();
//
//        double[] slicnosti = vsm.calculateObjectSimalarities(movieDataMatrix, 695);
//        for (int i = 0; i < slicnosti.length; i++) {
//            System.out.println("slicnost: " + slicnosti[i]);
//        }
//
//        DataModelManager.getInstance().closeDataModel();
        MovieRecommendationService mrs = new MovieRecommendationService();
        double[] niz = {0.3,0.3,0.4};
        mrs.suggestMovies("naslov", niz,5);
    }
}
