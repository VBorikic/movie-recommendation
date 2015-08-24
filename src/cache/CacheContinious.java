/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

/**
 *
 * @author vlada.borikic
 */
import com.hp.hpl.jena.rdf.model.Resource;
import domen.MovieRecommendation;
import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CacheContinious {

    List<MovieRecommendation> recommendations;

    public CacheContinious(List<MovieRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public void cacheToXML() {

        try {
            String filepath = "sugestions.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node rootElement = doc.getFirstChild();

		// Get the staff element , it may not working if tag has spaces, or
            // whatever weird characters in front...it's better to use
            // getElementsByTagName() to get it directly.
            // Node staff = company.getFirstChild();
//                Node lastMovie = movies.getLastChild();
            NodeList lastMovie = doc.getElementsByTagName("movie");
            int lastId = lastMovie.getLength() - 1;
            // update staff attribute
//		NamedNodeMap attr = lastMovie.getAttributes();
//		Node id = attr.getNamedItem("id");
//		int lastId =(int) Integer.parseInt(id.getTextContent());

            for (int i = 0; i < recommendations.size(); i++) {
                MovieRecommendation mr = recommendations.get(i);

                System.out.println("Film " + mr.getMovie().getURI());
                Element movie = doc.createElement("movie");

                Attr movieURI = doc.createAttribute("URI");
                movieURI.setValue(mr.getMovie().getURI());
                movie.setAttributeNode(movieURI);

                Attr movieID = doc.createAttribute("id");
                movieID.setValue((i + lastId + 1) + "");
                movie.setAttributeNode(movieID);

//                movie.setTextContent(mr.getMovie().getURI());
                rootElement.appendChild(movie);

                List<Resource> list = mr.getMovieSugestions();
                //write terms(MovieRecommendation
                for (Resource res : list) {
                    System.out.println("preporuka " + res.getURI());
                    Element nonTrTerm = doc.createElement("sugestion");
                    nonTrTerm.setTextContent(res.getURI());
//                    nonTrTerm.appendChild(doc.createTextNode(res.getURI()));
                    movie.appendChild(nonTrTerm);
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("File Updatad");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }
}
