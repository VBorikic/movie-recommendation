/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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

        
//  pozivanje aplikacije      
        MovieRecommendationService mrs = new MovieRecommendationService();
        double[] niz = {0.3, 0.8, 0.45};
        mrs.recommendMovies(niz, 5);

    }
}
