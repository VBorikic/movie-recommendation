/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domen.MovieProperty;
import domen.Result;
import domen.SimilarityValue;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

//        mde.testDataExtraction();
        //calculate results
        List<MovieProperty> movieProperties = Session.getInstance().getMovieProperties();
        Result res = new Result();
        calculateSimilarityValues(movieProperties);

        normalizeSimilarityValues(movieProperties, ponderValues, res);

        //sort results
//        Arrays.sort(globalSimalarityIndexes);
        Collections.sort(res.getSimilarities(), new Comparator<SimilarityValue>() {
            @Override
            public int compare(SimilarityValue sv1, SimilarityValue sv2) {
                //descending order
                return Double.compare(sv2.getSimilarity(), sv1.getSimilarity());
            }
        });

        //return sugestions
//        for (int i = globalSimalarityIndexes.length - 1; i > globalSimalarityIndexes.length - (resultsSize + 1); i--) {
//            //-1 zbog toga je sto je najbolji rezultat poredjenje filma sa samim sobom, pa ga iskljucujemo iz rezultata
//            movieSuggestions.add(movieTitle);
//        }
        System.out.println("rezultati");
        for (int i = 1; i < resultsSize + 1; i++) {
            System.out.println(res.getSimilarities().get(i).getMovie().getURI());
        }

        //return list<String> imena nekoliko najslicnijih filmova
        return null;
    }

    private void normalizeSimilarityValues(List<MovieProperty> movieProperties, double[] ponderValues, Result res) {

        int similarityVectorLength = movieProperties.get(0).getSimilarities().size();

        for (int i = 0; i < similarityVectorLength; i++) {
            //prepare movie set for results
            res.getSimilarities().get(i).setMovie(Session.getInstance().getMovies().get(i));
        }
//        double[] globalSimalarityIndexes = new double[similarityVectorLength];
//        for (int i = 0; i < movieProperties.size(); i++) {
//            double[] similarityArray = movieProperties.get(i).getSimilarities();
//            
//            for (int j = 0; j < similarityVectorLength; j++) {
//                globalSimalarityIndexes[j] += similarityArray[j] * ponderValues[i];
//            }
//        }
        for (int i = 0; i < movieProperties.size(); i++) {
            List<SimilarityValue> similarities = movieProperties.get(i).getSimilarities();
            for (int j = 0; j < similarities.size(); j++) {
                double value = res.getSimilarities().get(i).getSimilarity();
                value += similarities.get(i).getSimilarity() * ponderValues[i];
                res.getSimilarities().get(i).setSimilarity(value);
            }
        }
        System.out.println("similarities pondered.");
    }

    private void calculateSimilarityValues(List<MovieProperty> movieProperties) {
        VSMAlgorithm vsm = new VSMAlgorithm();
        for (MovieProperty movieProperty : movieProperties) {
            //izracunaj vektor slicnosti za jedan properti i setuj ga movieProperty objektu
//            movieProperty.setSimmilarityIndexes(vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), 695));
            double[] values = vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), 300);
            //set calculated values to list of similarities  movie-value pairs
            for (int i = 0; i < values.length; i++) {
                movieProperty.getSimilarities().get(i).setSimilarity(values[i]);
            }
        }
    }

}
