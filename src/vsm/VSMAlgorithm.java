/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsm;

/**
 *
 * @author Vlada
 */
public class VSMAlgorithm {

    double[] movieSimilarityIndexes;
    CosineSimilarity cs;
    TF_IDF tfidf;

    public VSMAlgorithm() {
        cs = new CosineSimilarity();
        tfidf = new TF_IDF();
    }

    /**
     *  Calculates similarity between pairs of movies from the input matrix
     * @param binaryMatrix shows if every movie contains certain property(actor, director etc) or not
     * @param chosenMovieRow position in binary matrix of movie that we compare to other movies
     * @return double[] array of movies similarity values; array values have to be between 0 and 1
     */
    public double[] calculateMovieSimalarities(int[][] binaryMatrix, int chosenMovieRow) {

        movieSimilarityIndexes = new double[binaryMatrix.length];


        double[][] normalizedTFIDFMatrix = tfidf.normalizeMatrixRows(tfidf.calculateTFIDF(binaryMatrix));

        for (int i = 0; i < movieSimilarityIndexes.length; i++) {
            movieSimilarityIndexes[i]
                    = cs.calculateCosineSimilarity(normalizedTFIDFMatrix[chosenMovieRow-1], normalizedTFIDFMatrix[i]);
        }
        return movieSimilarityIndexes;
    }
}
