package services;

import java.util.List;

import queries.QueryExecutor;

import persistence.DataModelManager;
import util.Constants;

public class MovieService {

    private QueryExecutor queryExecutor = new QueryExecutor();

    public List<String> getAllMovies() {
//        String queryString
//                = "PREFIX rdf: <" + Constants.RDF_NS + "> "
//                + "PREFIX dbpedia-owl: <" + Constants.DBPEDIA_OWL + "> "
//                + "PREFIX foaf: <" + Constants.FOAF_NS + "> "
//                + "SELECT ?name "
//                + "WHERE  { "
//                + "?film rdf:type dbpedia-owl:Film ;"
//                + "foaf:name ?name ."
//                + "}";
        String queryString
                = "PREFIX rdf: <" + Constants.RDF_NS + "> "
                + "PREFIX dbpedia-owl: <" + Constants.DBPEDIA_OWL + "> "
                + "PREFIX foaf: <" + Constants.FOAF_NS + "> "
                + "SELECT distinct ?film ?name "
                + "WHERE  { "
                + "?film rdf:type dbpedia-owl:Film ;"
                + "foaf:name ?name ."
                + "} ORDER BY ?name";
        return queryExecutor
                .executeSelectQueryOverModel(queryString, "name", "film",
                        DataModelManager.getInstance().getModel());
    }

}
