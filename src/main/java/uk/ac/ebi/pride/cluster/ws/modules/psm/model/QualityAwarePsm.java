package uk.ac.ebi.pride.cluster.ws.modules.psm.model;

/**
 * Psm with additional annotation on the quality of the cluster its belong to
 *
 * @author Rui Wang
 * @version $Id$
 */
public class QualityAwarePsm extends Psm {

    private String clusterQuality;

    public String getClusterQuality() {
        return clusterQuality;
    }

    public void setClusterQuality(String clusterQuality) {
        this.clusterQuality = clusterQuality;
    }
}
