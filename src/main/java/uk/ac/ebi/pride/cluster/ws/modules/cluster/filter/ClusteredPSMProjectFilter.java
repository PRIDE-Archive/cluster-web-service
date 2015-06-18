package uk.ac.ebi.pride.cluster.ws.modules.cluster.filter;

import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

/**
 * Filter PSMs by project accession
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPSMProjectFilter implements IPredicate<ClusteredPSMDetail> {

    private final ClusterDetail cluster;
    private final String projectAccession;

    public ClusteredPSMProjectFilter(ClusterDetail cluster, String projectAccession) {
        this.cluster = cluster;
        this.projectAccession = projectAccession;
    }

    @Override
    public boolean apply(ClusteredPSMDetail psm) {
        Long assayId = psm.getPsmDetail().getAssayId();
        AssayDetail assayDetail = cluster.getAssayDetail(assayId);

        return assayDetail.getProjectAccession().equalsIgnoreCase(projectAccession);
    }
}
