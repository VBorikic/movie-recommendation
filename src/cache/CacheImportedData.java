/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import domen.MovieProperty;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;
import session.Session;

/**
 *
 * @author vlada.borikic
 */
public class CacheImportedData {

    String cacheMovieDataLocation = "cache/movies.ser";
    String cacheMatricesDataLocation = "cache/matrices.ser";

    public void cacheData() {

        ObjectOutput output;
        ObjectOutput output2;
        try {
            output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(cacheMovieDataLocation)));
            output.writeObject(Session.getInstance().getMovies());
            output.close();

            output2 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(cacheMatricesDataLocation)));
            output2.writeObject(Session.getInstance().getMovieProperties());
            output2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isCached() {
        File movies = new File(cacheMovieDataLocation);
        File matrices = new File(cacheMovieDataLocation);
        if (movies.exists() && !movies.isDirectory() && matrices.exists() && !matrices.isDirectory()) {
            return true;
        }
        return false;
    }

    public void retrieveData() {

        ObjectInput input;
        ObjectInput input2;
        try {
            input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(cacheMovieDataLocation)));
            List<String> movies = (List<String>) input.readObject();
            Session.getInstance().setMovies(movies);
            input.close();

            input2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(cacheMatricesDataLocation)));
            List<MovieProperty> matrices = (List<MovieProperty>) input2.readObject();
            Session.getInstance().setMovieProperties(matrices);
            input2.close();

//            System.out.println("imported data");
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
