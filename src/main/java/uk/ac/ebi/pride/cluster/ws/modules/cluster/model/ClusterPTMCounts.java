package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import uk.ac.ebi.pride.cluster.ws.modules.assay.model.PTMCount;

import java.util.List;

/**
 * A list of PTM counts
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusterPTMCounts {

    private List<PTMCount> ptmCounts;

    public List<PTMCount> getPtmCounts() {
        return ptmCounts;
    }

    public void setPtmCounts(List<PTMCount> ptmCounts) {
        this.ptmCounts = ptmCounts;
    }
}
