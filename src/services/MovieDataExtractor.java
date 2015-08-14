/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import domen.MovieProperty;
import domen.SimilarityValue;
import java.util.ArrayList;
import java.util.List;
import session.Session;
import util.Constants;

/**
 *
 * @author Vlada
 */
public class MovieDataExtractor {

    public MovieDataExtractor() {
        
    }

    /**
     * Creates data matrix for certain property using RDF data, and sets it to
     * MovieProperty attribute at the end
     *
     * @param model RDF data model
     * @param mp MovieProperty
     */
    public void extractMovieDataFromModel(Model model, MovieProperty mp) {
        List<Resource> movies = new ArrayList<>();
         List<RDFNode> uniqueFeaturesForProperty = new ArrayList<>();;
        String propertyName = mp.getName();

        Property pr = null;
        //inicijalizuj property za poredjenje
        if (propertyName.equals("subject")) {
            pr = new PropertyImpl(Constants.DC_TERMS + propertyName);
        } else {
            pr = new PropertyImpl(Constants.DBPEDIA_OWL + propertyName);
        }

        StmtIterator iter = model.listStatements(null, pr, (RDFNode) null);

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property pred = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();

            if (!movies.contains(subject)) {
                SimilarityValue sv = new SimilarityValue();
                sv.setMovie(subject);
                mp.getSimilarities().add(sv);
                movies.add(subject);
            }
            if (!uniqueFeaturesForProperty.contains(object)) {
                uniqueFeaturesForProperty.add(object);
            }
        }

        System.out.println("property: " + propertyName);
        System.out.println("broj filmova: " + movies.size());
        if (Session.getInstance().getMovies() == null) {
            Session.getInstance().setMovies(movies);
        }

        int[][] movieMatrix = new int[movies.size()][uniqueFeaturesForProperty.size()];

        StmtIterator iter2 = model.listStatements();
        while (iter2.hasNext()) {
            Statement stmt = iter2.next();
            Resource subject = stmt.getSubject();
//            Property pred = stmt.getPredicate();
            RDFNode object = stmt.getObject();
            if (stmt.getPredicate().equals(pr)) {
                for (int i = 0; i < movieMatrix.length; i++) {
                    for (int j = 0; j < movieMatrix[0].length; j++) {
                        if (movies.get(i).equals(subject) && uniqueFeaturesForProperty.get(j).equals(object)) {
                            movieMatrix[i][j] = 1;
                        }
                    }
                }
            }
        }
//        return movieMatrix;
        mp.setDataMatrix(movieMatrix);
        System.out.println("Popunjena matrica "+propertyName);
    }

    public void testDataExtraction() {
        for (int i = 0; i < Session.getInstance().getMovieProperties().size(); i++) {
            int[][] data = Session.getInstance().getMovieProperties().get(i).getDataMatrix();
            System.out.println("matrica: "+i+" broj redova "+data.length+" broj kolona "+data[0].length);
            for (int j = 0; j < data.length; j++) {
                for (int k = 0; k < data[0].length; k++) {
                    if (data[j][k]!=0 && data[j][k]!=1) {
                        System.out.println("podatak razlicit od 0 i 1!");
                        return;
                    }
                }
            }
        }

    }
}
