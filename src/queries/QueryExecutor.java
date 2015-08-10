package queries;

import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class QueryExecutor {

    public List<String> executeSelectQueryOverModel(String query,
            String var1, String var2, Model model) {

        // Execute the query over the model
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        return executeSelectQuery(query, var1, var2, qe);
    }

    public List<String> executeSelectQuery(String query, String var1, String var2, QueryExecution qe) {
        // Execute the query over the model
        ResultSet resultSet = qe.execSelect();

        List<String> results = new LinkedList<>();

        // obtain results from the result set
        while (resultSet.hasNext()) {
            QuerySolution solution = resultSet.nextSolution();
            RDFNode value1 = solution.get(var1);
            RDFNode value2 = solution.get(var2);
            String film = "Naziv filma: ";
            if (value1.isLiteral()) {
                film += ((Literal) value1).getLexicalForm();
//                results.add(((Literal) value1).getLexicalForm());
            } else {
//                results.add(((Resource) value1).getURI());
                film += ((Resource) value1).getURI();
            }
            film += "\tURI: ";
            if (value2.isLiteral()) {
                film += ((Literal) value2).getLexicalForm();
            } else {
                film += ((Resource) value2).getURI();
            }
            results.add(film);
        }
        qe.close();

        return results;
    }

}
