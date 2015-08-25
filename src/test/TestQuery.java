/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import persistence.DataModelManager;
import services.MovieService;

/**
 *
 * @author Vlada
 */
public class TestQuery {

    public static void main(String[] args) {

        MovieService queryService = new MovieService();

        // loading data
        DataModelManager.getInstance().importData("data" + File.separator + "data.rdf", "RDF/XML");
        List<String> movies = queryService.getAllMovies();

        if (movies != null) {
            for (String m : movies) {

                System.out.println(m);
            }
        }

        DataModelManager.getInstance().closeDataModel();
    }
}
