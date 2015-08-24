/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import cache.CacheRecommendations;
import com.hp.hpl.jena.rdf.model.Resource;
import domen.MovieProperty;
import domen.MovieRecommendation;
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

    public void suggestMovies(double[] ponderValues, int resultsSize) {
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
        
        for (int i = 0; i < 9; i++) {
        calculateOneMovie(movieProperties, ponderValues,Session.getInstance().getMovies().get(i));
        }
        //write sugestions to XML
        CacheRecommendations cr = new CacheRecommendations(Session.getInstance().getRecommendations());
        cr.cacheToXML();
    }

    private void calculateOneMovie(List<MovieProperty> movieProperties, double[] ponderValues, Resource movie) {
        Result res = new Result();
        int movieNumber = Session.getInstance().getMovieNumberFromList(movie);
        calculateSimilarityValues(movieProperties, movieNumber);

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

        MovieRecommendation mr = new MovieRecommendation();
        mr.setMovie(null);
        List<Resource> list = new ArrayList<>();
        //add 10 recommendations
        for (int i = 1; i < 11; i++) {
            list.add(res.getSimilarities().get(i).getMovie());
        }
        mr.setMovieSugestions(list);
        //add recommendation to list
        Session.getInstance().getRecommendations().add(mr);
    }

    private void normalizeSimilarityValues(List<MovieProperty> movieProperties, double[] ponderValues, Result res) {

        int similarityVectorLength = movieProperties.get(0).getSimilarities().size();

        for (int i = 0; i < similarityVectorLength; i++) {
            //prepare movie set for results
            res.getSimilarities().add(new SimilarityValue());
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

    private void calculateSimilarityValues(List<MovieProperty> movieProperties, int movieFromList) {
        VSMAlgorithm vsm = new VSMAlgorithm();
        for (MovieProperty movieProperty : movieProperties) {
            //izracunaj vektor slicnosti za jedan properti i setuj ga movieProperty objektu
//            movieProperty.setSimmilarityIndexes(vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), 695));
            double[] values = vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), movieFromList);
            //set calculated values to list of similarities  movie-value pairs
            for (int i = 0; i < values.length; i++) {
//                movieProperty.getSimilarities().add(new SimilarityValue());
                movieProperty.getSimilarities().get(i).setSimilarity(values[i]);
            }
        }
    }

}
