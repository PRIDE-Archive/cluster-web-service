package uk.ac.ebi.pride.cluster.ws.modules.psm.util;

import uk.ac.ebi.pride.cluster.ws.modules.psm.model.Psm;
import uk.ac.ebi.pride.cluster.ws.modules.psm.model.PsmList;
import uk.ac.ebi.pride.cluster.ws.modules.psm.model.QualityAwarePsm;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public final class RepoPsmToWsPsmMapper {

    public static PsmList asPsmList(List<ClusteredPSMDetail> repoPsms) {
        PsmList psmList = new PsmList();

        for (ClusteredPSMDetail repoPsm: repoPsms) {
            Psm psm = new Psm();

            psm.setId(repoPsm.getPsmId());
            psm.setClusterId(repoPsm.getClusterId());
            psm.setSequence(repoPsm.getSequence());
            psm.setRatio(repoPsm.getPsmRatio());
            psm.setSpectrumId(repoPsm.getSpectrumId());

            psmList.addPsm(psm);
        }

        return psmList;
    }

    public static QualityAwarePsm asQualityAwarePsm(ClusteredPSMDetail repoPsm, ClusterSummary repoClusterSummary) {
        QualityAwarePsm qualityAwarePsm = new QualityAwarePsm();

        qualityAwarePsm.setClusterQuality(repoClusterSummary.getQuality().toString());
        qualityAwarePsm.setClusterId(repoClusterSummary.getId());
        qualityAwarePsm.setRatio(repoPsm.getPsmRatio());
        qualityAwarePsm.setId(repoPsm.getPsmId());
        qualityAwarePsm.setSequence(repoPsm.getSequence());
        qualityAwarePsm.setSpectrumId(repoPsm.getSpectrumId());

        return qualityAwarePsm;
    }
}
