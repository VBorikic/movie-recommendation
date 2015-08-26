/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cache.CacheImportedData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import services.BeautifyURI;
import services.MovieRecommendationService;
import session.Session;

/**
 *
 * @author vlada.borikic
 */
public class Main {

    public static void main(String[] args) {

        boolean exit = false;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("Please wait, Movie list is loading...");

        MovieRecommendationService mrs = new MovieRecommendationService();
        CacheImportedData cid = new CacheImportedData();
        if (cid.isCached()) {
            cid.retrieveData();
//            System.out.println("Data retrived");
        } else {
            mrs.populateDataFromDataset();
            cid.cacheData();
//            System.out.println("data cached to file.");
        }

        while (!exit) {
            List<String> movies = Session.getInstance().getMovies();
            System.out.println("Movie list:\n");
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ") " + BeautifyURI.beautify(movies.get(i)));
            }
            System.out.println("\nInsert the number of desired movie from the Movie list:");
            try {
                String movieNumber = br.readLine();
                int numberFromList = Integer.parseInt(movieNumber) - 1;

                System.out.println("Insert significance weights(0.0-1.0) for starring, director and movie subject(ex. 0.35 0.41 0.86) : ");

                String ponderValues = br.readLine();
                String[] ponders = ponderValues.split(" ");

                double[] ponderNumbers = {Double.parseDouble(ponders[0]), Double.parseDouble(ponders[1]), Double.parseDouble(ponders[2])};

                System.out.println("Finaly, insert number of suggestions you want(1-10):");
                String StringNumOfRec = br.readLine();
                int numberOfRec = Integer.parseInt(StringNumOfRec);

                List<String> recommendations = mrs.recommend(numberFromList, ponderNumbers, numberOfRec);

                System.out.println("\nMovies similar to movie " + BeautifyURI.beautify(recommendations.get(0)) + " :");
                for (int i = 1; i < recommendations.size(); i++) {
                    System.out.println(BeautifyURI.beautify(recommendations.get(i)));
                }

                System.out.println("\nType N for new recommendation, or X for exit!");
                String command = br.readLine();

                char key = command.charAt(0);

                if (key == 'X' || key == 'x') {
                    exit = true;
                }
                System.out.println("---END---");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
