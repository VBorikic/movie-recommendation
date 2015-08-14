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

    double[] objectSimilarityIndexes;
    CosineSimilarity cs;
    TF_IDF tfidf;

    public VSMAlgorithm() {
        cs = new CosineSimilarity();
        tfidf = new TF_IDF();
    }

    /**
     * Calculates similarity between pairs of objects from the input matrix
     *
     * @param binaryMatrix shows if every objects contains certain
     * property/feature or not
     * @param chosenObjectRow position in binary matrix of object that we
     * compare to other objects
     * @return double[] array of objects similarity values; array values have to
     * be between 0 and 1
     */
    public double[] calculateObjectSimalarities(int[][] binaryMatrix, int chosenObjectRow) {

        objectSimilarityIndexes = new double[binaryMatrix.length];

        double[][] normalizedTFIDFMatrix = tfidf.normalizeMatrixRows(tfidf.calculateTFIDF(binaryMatrix));

        for (int i = 0; i < objectSimilarityIndexes.length; i++) {
            objectSimilarityIndexes[i]
                    = cs.calculateCosineSimilarity(normalizedTFIDFMatrix[chosenObjectRow - 1], normalizedTFIDFMatrix[i]);
        }
        return objectSimilarityIndexes;
    }
}
