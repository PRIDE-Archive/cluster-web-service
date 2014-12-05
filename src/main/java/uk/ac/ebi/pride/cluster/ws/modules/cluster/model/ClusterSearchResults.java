package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class ClusterSearchResults {

    private long totalResults;
    private long pageNumber;
    private long pageSize;
    private List<Cluster> results;

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
}
