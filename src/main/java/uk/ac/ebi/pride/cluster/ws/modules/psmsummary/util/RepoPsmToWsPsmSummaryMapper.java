package uk.ac.ebi.pride.cluster.ws.modules.psmsummary.util;

import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.model.ClusterSummary;
import uk.ac.ebi.pride.cluster.ws.modules.psmsummary.model.PsmSummary;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;
import uk.ac.ebi.pride.spectracluster.repo.utils.paging.Page;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class RepoPsmToWsPsmSummaryMapper {

    public static List<PsmSummary> asPsmSummaryList(List<ClusteredPSMDetail> repoPsms) {
        List<PsmSummary> res = new LinkedList<PsmSummary>();

        for (ClusteredPSMDetail repoPsm: repoPsms) {
            PsmSummary psmSummary = new PsmSummary();

            psmSummary.setId(repoPsm.getPsmId());
            psmSummary.setClusterId(repoPsm.getClusterId());
            psmSummary.setSequence(repoPsm.getSequence());
            psmSummary.setRatio(repoPsm.getPsmRatio());
            psmSummary.setSpectrumId(repoPsm.getSpectrumId());

            res.add(psmSummary);
        }

        return res;
    }


}
