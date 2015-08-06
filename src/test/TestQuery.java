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
//        DataModelManager.getInstance().getModel().write(System.out, "TURTLE");
//        int brojac = 0;
        int duplicates = 0;
        List<String> movies = queryService.getAllMovies();
        List<String> noDUplicateMlovies = new ArrayList<>();
        if (movies != null) {
            for (String m : movies) {
                if (!noDUplicateMlovies.contains(m)) {
                    noDUplicateMlovies.add(m);
                } else {
                    duplicates++;
                }
                System.out.println(m);
//                brojac++;
            }
        }
        System.out.println("Model sadrzi " + noDUplicateMlovies.size() + " filmova.");
        System.out.println("Model sadrzi " + duplicates + " duplikata.");

        DataModelManager.getInstance().closeDataModel();
    }
}
