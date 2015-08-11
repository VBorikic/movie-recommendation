/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domen.MovieProperty;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import persistence.DataModelManager;
import session.Session;
import vsm.VSMAlgorithm;

/**
 *
 * @author Vlada
 */
public class MovieRecommendationService {

    public List<String> movieSuggestions;

    public MovieRecommendationService() {
        movieSuggestions = new ArrayList<>();
    }

    public List<String> suggestMovies(String movieTitle, double[] ponderValues, int resultsSize) {
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

        List<MovieProperty> movieProperties = Session.getInstance().getMovieProperties();

        calculateSimilarityValues(movieProperties);

        double[] globalSimalarityIndexes = normalizeSimilarityValues(movieProperties, ponderValues);
        //sort results
        Arrays.sort(globalSimalarityIndexes);

        for (int i = globalSimalarityIndexes.length - 1; i > globalSimalarityIndexes.length - (resultsSize + 1); i--) {
            //-1 zbog toga je sto je najbolji rezultat poredjenje filma sa samim sobom, pa ga iskljucujemo iz rezultata
            movieSuggestions.add(movieTitle);
        }

        //return list<String> imena nekoliko najslicnijih filmova
        return null;
    }

    private double[] normalizeSimilarityValues(List<MovieProperty> movieProperties, double[] ponderValues) {
        int similarityVectorLength = movieProperties.get(0).getSimmilarityIndexes().length;
        double[] globalSimalarityIndexes = new double[similarityVectorLength];
        for (int i = 0; i < movieProperties.size(); i++) {
            double[] similarityArray = movieProperties.get(i).getSimmilarityIndexes();
            
            for (int j = 0; j < similarityVectorLength; j++) {
                globalSimalarityIndexes[j] += similarityArray[j] * ponderValues[i];
            }
        }
        return globalSimalarityIndexes;
    }

    private void calculateSimilarityValues(List<MovieProperty> movieProperties) {
        VSMAlgorithm vsm = new VSMAlgorithm();
        for (MovieProperty movieProperty : movieProperties) {
//            double[] simmilarityValues = vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), 695);
            //izracunaj vektor slicnosti za jedan properti i setuj ga movieProperty objektu
            movieProperty.setSimmilarityIndexes(vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), 695));
        }
    }

}
