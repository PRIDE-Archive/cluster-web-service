package uk.ac.ebi.pride.cluster.ws.modules.clustersummary.model;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class ClusterSummarySearchResults {

    private long totalResults;
    private long pageNumber;
    private long pageSize;
    private List<ClusterSummary> results;

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

    public List<ClusterSummary> getResults() {
        return results;
    }

    public void setResults(List<ClusterSummary> results) {
        this.results = results;
    }
}
