package uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.util;

import uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.model.ClusterDetail;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.model.ClusterSummary;
import uk.ac.ebi.pride.spectracluster.repo.utils.paging.Page;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class RepoClusterToWsClusterDetailMapper {


    public static ClusterDetail asClusterDetail(uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary repoCluster) {
        ClusterDetail res = new ClusterDetail();

        res.setId(repoCluster.getId());
        res.setNumberOfSpectra(repoCluster.getNumberOfSpectra());
        res.setAveragePrecursorCharge(repoCluster.getAveragePrecursorCharge());
        res.setAveragePrecursorMz(repoCluster.getAveragePrecursorMz());
        res.setConsensusSpectrumIntensity(repoCluster.getConsensusSpectrumIntensity());
        res.setConsensusSpectrumMz(repoCluster.getConsensusSpectrumMz());
        res.setMaxRatio(repoCluster.getMaxPeptideRatio());

        return res;
    }
}
