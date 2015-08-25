/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import com.hp.hpl.jena.rdf.model.Resource;
import domen.MovieRecommendation;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author vlada.borikic
 */
public class CacheRecommendations {

    private List<MovieRecommendation> recommendations;

    public CacheRecommendations(List<MovieRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public void cacheToXML() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Movies");
            doc.appendChild(rootElement);
            List<String> formulaIDs = new ArrayList<String>();
            System.out.println(recommendations.size());
            for (int i = 0; i < recommendations.size(); i++) {
                MovieRecommendation mr = recommendations.get(i);

                System.out.println("Film " + mr.getMovie().getURI());
                Element movie = doc.createElement("movie");

                Attr movieURI = doc.createAttribute("URI");
                movieURI.setValue(mr.getMovie().getURI());
                movie.setAttributeNode(movieURI);

 
                Attr movieID = doc.createAttribute("id");
                movieID.setValue(i + "");
                movie.setAttributeNode(movieID);

                rootElement.appendChild(movie);

                List<Resource> list = mr.getMovieSugestions();
                //write terms(MovieRecommendation
                for (Resource res : list) {
                    System.out.println("preporuka " + res.getURI());
                    Element nonTrTerm = doc.createElement("sugestion");
                    nonTrTerm.setTextContent(res.getURI());
                    movie.appendChild(nonTrTerm);
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("sugestions.xml"));

            transformer.transform(source, result);

            System.out.println("XML file saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }

    }
}
