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
public class CosineSimilarity {

    /**
     *  Calculates cosine similarity between two normalized vectors.
     * @param vectorA first vector
     * @param vectorB second vector
     * @return cosine similarity value
     */
    public double calculateCosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normVectorA = 0.0;
        double normVectorB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normVectorA += Math.pow(vectorA[i], 2);
            normVectorB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normVectorA) * Math.sqrt(normVectorB));
    }
}
