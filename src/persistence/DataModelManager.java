/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import java.util.Date;
import org.apache.jena.riot.RDFDataMgr;

/**
 *
 * @author Vlada
 */
public class DataModelManager {

    private static final String directory = "tdb";
    private Dataset dataset;

    private static DataModelManager INSTANCE;

    private DataModelManager() {
//        dataset = RDFDataMgr.loadDataset("data/data_min.rdf");
//        System.out.println("--- Import finished successfully --- " + new Date());
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
        System.out.println("Importing data - " + new Date());
//        FileManager.get().readModel(dataset.getDefaultModel(), filename, syntax);
        dataset = RDFDataMgr.loadDataset("data/data_min.rdf");
        System.out.println("Import finished - " + new Date());
    }

    public void closeDataModel() {
        dataset.close();
    }
}
