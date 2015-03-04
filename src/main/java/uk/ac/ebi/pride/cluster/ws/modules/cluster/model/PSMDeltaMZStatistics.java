package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Keep two sets of delta m/z values, one for the entire cluster and one for the high ratio peptides
 *
 * @author Rui Wang
 * @version $Id$
 */
public class PSMDeltaMZStatistics {
    // delta m/z values for the PSMs with the highest ratio peptide sequence in the cluster
    private final List<Float> highRatioPeptideDeltaMzs = new ArrayList<Float>();

    // delta m/z values for all the PSMs in the cluster
    private final List<Float> clusterDeltaMz = new ArrayList<Float>();

    public List<Float> getHighRatioPeptideDeltaMzs() {
        return highRatioPeptideDeltaMzs;
    }

    public void addHighRatioPeptideDeltaMz(Float deltaMz) {
        highRatioPeptideDeltaMzs.add(deltaMz);
    }

    public List<Float> getClusterDeltaMz() {
        return clusterDeltaMz;
    }

    public void addClusterDeltaMz(Float deltaMz) {
        clusterDeltaMz.add(deltaMz);
    }


}
