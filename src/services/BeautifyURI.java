/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author vlada.borikic
 */
public class BeautifyURI {

    public static String beautify(String URI){
        String URIwithout_ = URI.replace('_', ' ');
        String newURI = URIwithout_.replaceFirst("http://dbpedia.org/resource/", "");
        return newURI;
    }
}
