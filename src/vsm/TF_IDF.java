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
public class TF_IDF {

    /**
     * Calculates tfidf weights of matrix;
     * @param tfMatrix contains only values 0 and 1;
     * @return double[][] matrix of tfidf weights.
     */
    public double[][] calculateTFIDF(int[][] tfMatrix) {

        int numOfObjects = tfMatrix.length;
        int numOfFeatures = tfMatrix[0].length;

        double[][] tfidfMatrix = new double[numOfObjects][numOfFeatures];

        int[] counters = new int[numOfFeatures];
        System.out.println("calculating tfidf for matrix");
        for (int i = 0; i < numOfObjects; i++) {

            // counts number of objects that one feature has a connection to
            for (int j = 0; j < tfMatrix[i].length; j++) {
                if (tfMatrix[i][j] == 1) {
                    counters[j]++;
                }
            }
        }
//        for (int i = 0; i < counters.length; i++) {
//            
//        System.out.println("counter value: " + counters[i]);
//        }
        for (int i = 0; i < numOfObjects; i++) {
            for (int j = 0; j < tfMatrix[i].length; j++) {
                double logValue = (double) numOfObjects / counters[j];
                tfidfMatrix[i][j] = tfMatrix[i][j] * Math.log(logValue);
//                System.out.println(i + "," + j + "= " + tfidfMatrix[i][j]);
            }
        }
        return tfidfMatrix;
    }

    /**
     *  Normalizes every input matrix row.
     * @param matrix of tfidf weights.
     * @return double[][] matrix of normalized values.
     */
    public double[][] normalizeMatrixRows(double[][] matrix) {

        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;

        double[][] normalizedMatrix = new double[numOfRows][numOfColumns];

        for (int i = 0; i < numOfRows; i++) {
            double normValue = 0;
            double testSum = 0;
            for (int j = 0; j < numOfColumns; j++) {              
                normValue += Math.pow(matrix[i][j], 2);               
            }
            
            for (int j = 0; j < numOfColumns; j++) {
                normalizedMatrix[i][j] = matrix[i][j] / (Math.sqrt(normValue));               
                testSum += Math.pow(matrix[i][j] / (Math.sqrt(normValue)),2);
            }
//           System.out.println("row " + i + ": " + Math.sqrt(testSum)); // has to be 1.0
        }

        return normalizedMatrix;
    }

}
