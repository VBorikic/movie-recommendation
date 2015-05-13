/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
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
        int brojac = 0;
        		List<String> movies = queryService.getAllMovies();
		
		if (movies != null) {
			for (String m : movies) {
				System.out.println(m);
                                brojac++;
			}
		}
                System.out.println("Model sadrzi "+brojac+" filmova.");
        
        
        DataModelManager.getInstance().closeDataModel();
    }
}
