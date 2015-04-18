package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 * @author ntoro <ntoro@ebi.ac.uk>

 */
public class ClusterSearchResults {

    private long totalResults;
    private long pageNumber;
    private long pageSize;
    private List<Cluster> results;

    //Map<ClusterId, Map<HighlightedField, HighlightSubstitution>
    private Map<Long, Map<String, List<String>>> highlightsMap = new HashMap<Long, Map<String, List<String>>>();

    //Map<FacetField, Map<FacetValue, CountForTheFacet>
    private Map<String, Map<String, Long>> facetsMap = new HashMap<String, Map<String, Long>>();

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<Cluster> getResults() {
        return results;
    }

    public void setResults(List<Cluster> results) {
        this.results = results;
    }

    public Map<String, Map<String, Long>> getFacetsMap() {
        return facetsMap;
    }

    public void setFacetsMap(Map<String, Map<String, Long>> facetsMap) {
        this.facetsMap = facetsMap;
    }

    public void addFacet(String facetType, Map<String, Long> facetMap) {
          if(facetType!=null && facetMap!= null && !facetMap.isEmpty()){
              this.facetsMap.put(facetType, facetMap);
          }
    }

    public Map<Long, Map<String, List<String>>> getHighlightsMap() {
        return highlightsMap;
    }

    public void setHighlightsMap(Map<Long, Map<String, List<String>>> highlightsMap) {
        this.highlightsMap = highlightsMap;
    }

    public void addHighlight(Long clusterId, Map<String, List<String>> highlights) {
        if(clusterId!=null && highlights!= null && !highlights.isEmpty()){
            this.highlightsMap.put(clusterId, highlights);
        }
    }
}
