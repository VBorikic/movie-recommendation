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
import domen.SimilarityMovieValuePair;
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

//    public void recommendMovies(double[] ponderValues, int resultsSize) {
//        populateDataFromDataset();
//        //calculate results
//        List<MovieProperty> movieProperties = Session.getInstance().getMovieProperties();
//        
//        for (int i = 0; i < Session.getInstance().getMovies().size(); i++) {
//            calculateOneMovie(movieProperties, ponderValues, Session.getInstance().getMovies().get(i),resultsSize);
//        }
//    }

    public List<String> recommend(int movieNumber, double[] ponderValues, int resultsSize) {
        List<MovieProperty> movieProperties = Session.getInstance().getMovieProperties();

        List<Resource> movies = calculateOneMovie(movieProperties, ponderValues, Session.getInstance().getMovies().get(movieNumber),resultsSize);

        List<String> movieNames = new ArrayList<>();

        for (Resource movie : movies) {
            movieNames.add(movie.getURI());
        }
        return movieNames;
    }

    public void populateDataFromDataset() {
        //import data into model/dataset
        DataModelManager.getInstance().importData("data" + File.separator + "data2.rdf", "RDF/XML");

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
    }

    private List<Resource> calculateOneMovie(List<MovieProperty> movieProperties, double[] ponderValues, Resource movie, int numOFrecemmendations) {
        Result res = new Result();
        int movieNumber = Session.getInstance().getMovieNumberFromList(movie);
        calculateSimilarityValues(movieProperties, movieNumber);

        normalizeSimilarityValues(movieProperties, ponderValues, res);
        //sort results
        Collections.sort(res.getSimilarities(), new Comparator<SimilarityMovieValuePair>() {
            @Override
            public int compare(SimilarityMovieValuePair sv1, SimilarityMovieValuePair sv2) {
                //descending order
                return Double.compare(sv2.getSimilarity(), sv1.getSimilarity());
            }
        });
        MovieRecommendation mr = new MovieRecommendation();
        mr.setMovie(movie);
        List<Resource> list = new ArrayList<>();
        //add x recommendations
        for (int i = 0; i < numOFrecemmendations + 1; i++) {
            list.add(res.getSimilarities().get(i).getMovie());
        }
        mr.setMovieSugestions(list);
        //add recommendation to list
        Session.getInstance().getRecommendations().add(mr);

        return list;
    }

    private void normalizeSimilarityValues(List<MovieProperty> movieProperties, double[] ponderValues, Result res) {

        int similarityVectorLength = movieProperties.get(0).getSimilarities().size();

        for (int i = 0; i < similarityVectorLength; i++) {
            //prepare movie set for results
            res.getSimilarities().add(new SimilarityMovieValuePair());
            res.getSimilarities().get(i).setMovie(Session.getInstance().getMovies().get(i));
        }
        for (int i = 0; i < movieProperties.size(); i++) {
            List<SimilarityMovieValuePair> similarities = movieProperties.get(i).getSimilarities();
            for (int j = 0; j < similarities.size(); j++) {
                double value = res.getSimilarities().get(j).getSimilarity();
                value += similarities.get(j).getSimilarity() * ponderValues[i];
                res.getSimilarities().get(j).setSimilarity(value);
            }
        }
    }

    private void calculateSimilarityValues(List<MovieProperty> movieProperties, int movieFromList) {
        VSMAlgorithm vsm = new VSMAlgorithm();
        for (MovieProperty movieProperty : movieProperties) {
            //calculate simillarity  vector
            double[] values = vsm.calculateObjectSimalarities(movieProperty.getDataMatrix(), movieFromList);
            //set calculated values to list of similarities  movie-value pairs
            for (int i = 0; i < values.length; i++) {
                movieProperty.getSimilarities().get(i).setSimilarity(values[i]);
            }
        }
    }

}
