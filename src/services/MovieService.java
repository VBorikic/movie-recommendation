package services;

import java.util.ArrayList;
import java.util.List;

import queries.QueryExecutor;

import com.hp.hpl.jena.rdf.model.Model;
import persistence.DataModelManager;
import util.Constants;

public class MovieService {

    private QueryExecutor queryExecutor = new QueryExecutor();

    public List<String> getAllMovies() {
        String queryString
                = "PREFIX rdf: <" + Constants.RDF_NS + "> "
                + "PREFIX dbpedia-owl: <" + Constants.DBPEDIA_OWL + "> "
                + "PREFIX foaf: <" + Constants.FOAF_NS + "> "
                + "SELECT distinct ?name "
                + "WHERE  { "
                + "?film rdf:type dbpedia-owl:Film ;"
                + "foaf:name ?name ."
                + "}";

        return queryExecutor
                .executeSelectQueryOverModel(queryString, "name",
                        DataModelManager.getInstance().getModel());
    }

}
