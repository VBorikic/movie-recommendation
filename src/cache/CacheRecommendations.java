/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import domen.MovieRecommendation;
import java.util.List;

/**
 *
 * @author vlada.borikic
 */
public class CacheRecommendations {
    
    private List<MovieRecommendation> recommendations;

    public CacheRecommendations(List<MovieRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<MovieRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<MovieRecommendation> recommendations) {
        this.recommendations = recommendations;
    }
    
    
    
    public void cacheToXML(){
    
    
    }
}
