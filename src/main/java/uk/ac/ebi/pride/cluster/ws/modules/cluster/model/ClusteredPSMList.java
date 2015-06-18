package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import java.util.List;

/**
 * A list of paginated PSMs
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPSMList {

    private long totalResults;
    private long pageNumber;
    private long pageSize;
    private List<ClusteredPSM> psms;


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

    public List<ClusteredPSM> getPsms() {
        return psms;
    }

    public void setPsms(List<ClusteredPSM> psms) {
        this.psms = psms;
    }
}
