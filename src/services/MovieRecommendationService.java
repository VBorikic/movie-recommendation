/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domen.MovieProperty;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import persistence.DataModelManager;
import session.Session;

/**
 *
 * @author Vlada
 */
public class MovieRecommendationService {

    public List<String> movieSuggestions;

    public MovieRecommendationService() {
        movieSuggestions = new ArrayList<>();
    }

    public List<String> suggestMovies(String movieTitle, int[] ponderValues) {
        //import data into model/dataset
        DataModelManager.getInstance().importData("data" + File.separator + "data.rdf", "RDF/XML");

        MovieDataExtractor mde = new MovieDataExtractor();

        String[] propertyNames = new String[3];
        propertyNames[0] = "starring";
        propertyNames[1] = "director";
        propertyNames[2] = "subject";
        //extract data from rdf dataset to matrixes       
        for (int i = 0; i < propertyNames.length; i++) {

            MovieProperty mp = new MovieProperty();
            mp.setName(propertyNames[i]);
            Session.getInstance().addMovieProperty(mp);

            mde.extractMovieDataFromModel(DataModelManager.getInstance().getModel(), mp);
        }

//        VSMAlgorithm vsm = new VSMAlgorithm();
//        List<MovieProperty> movieProperties = Session.getInstance().getMovieProperties();
//
//        for (MovieProperty movieProperty : movieProperties) {
//
//            double[] simmilarityValues = vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), 695);
//        }
        
        //TODO izracunaj globalni similarity index vektor na osnovu pojedinacnih similarity vektora i vrati rezultat
        //return list<String> imena nekoliko najslicnijih filmova
        return null;
    }
}
