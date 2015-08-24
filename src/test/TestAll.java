/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import cache.CacheContinious;
import cache.CacheRecommendations;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import domen.MovieRecommendation;
import java.util.ArrayList;
import java.util.List;
import services.MovieRecommendationService;

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
        
//  pozivanje aplikacije      
        MovieRecommendationService mrs = new MovieRecommendationService();
        double[] niz = {0.3, 0.3, 0.4};
        mrs.suggestMovies(niz, 5);

//        List<MovieRecommendation> list = new ArrayList<>();
//
//        MovieRecommendation mr = new MovieRecommendation();
//        List<Resource> listaPreporuka = new ArrayList<>();
//        Resource film = new ResourceImpl("film proba 3");
//        mr.setMovie(film);
//        for (int i = 0; i < 4; i++) {
//            Resource pr = new ResourceImpl("preporuka " + (i + 1));
//            listaPreporuka.add(pr);
//        }
//        mr.setMovieSugestions(listaPreporuka);
//        list.add(mr);
//        
//                MovieRecommendation mr2 = new MovieRecommendation();
//        List<Resource> listaPreporuka2 = new ArrayList<>();
//        Resource film2 = new ResourceImpl("film proba 4");
//        mr2.setMovie(film2);
//        for (int i = 0; i < 4; i++) {
//            Resource pr = new ResourceImpl("preporuka - 2 - " + (i + 1));
//            listaPreporuka2.add(pr);
//        }
//        mr2.setMovieSugestions(listaPreporuka2);
//        list.add(mr2);
//        
////        CacheRecommendations cr = new CacheRecommendations(list);
////        cr.cacheToXML();
//        CacheContinious cc = new CacheContinious(list);
//        cc.cacheToXML();
    }
}
