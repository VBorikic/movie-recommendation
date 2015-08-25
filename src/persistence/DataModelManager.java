/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;

/**
 *
 * @author Vlada
 */
public class DataModelManager {

    private Dataset dataset;

    private static DataModelManager INSTANCE;

    private DataModelManager() {
    }

    public static DataModelManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataModelManager();
        }
        return INSTANCE;
    }

    public Model getModel() {
        return dataset.getDefaultModel();
    }

    public void importData(String filename, String syntax) {
        dataset = RDFDataMgr.loadDataset("data/data2.rdf");
    }

    public void closeDataModel() {
        dataset.close();
    }
}
