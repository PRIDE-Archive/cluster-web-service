package uk.ac.ebi.pride.cluster.ws.util;

import uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.model.ClusterDetail;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.model.ClusterSummary;
import uk.ac.ebi.pride.spectracluster.repo.utils.paging.Page;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class RepoClusterToWsClusterMapper {

    public static List<ClusterSummary> asClusterSummaryList(Page<uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary> repoClusters) {
        List<ClusterSummary> res = new LinkedList<ClusterSummary>();

        for (uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary repoCluster: repoClusters.getPageItems()) {
            ClusterSummary newClusterSummary = new ClusterSummary();

            newClusterSummary.setId(repoCluster.getId());
            newClusterSummary.setNumberOfSpectra(repoCluster.getNumberOfSpectra());
            newClusterSummary.setAveragePrecursorCharge(repoCluster.getAveragePrecursorCharge());
            newClusterSummary.setAveragePrecursorMz(repoCluster.getAveragePrecursorMz());
            newClusterSummary.setMaxRatio(repoCluster.getMaxPeptideRatio());

            res.add(newClusterSummary);
        }

        return res;
    }

    public static ClusterSummary asClusterSummary(uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary repoCluster) {

        ClusterSummary res = new ClusterSummary();

        res.setId(repoCluster.getId());
        res.setNumberOfSpectra(repoCluster.getNumberOfSpectra());
        res.setAveragePrecursorCharge(repoCluster.getAveragePrecursorCharge());
        res.setAveragePrecursorMz(repoCluster.getAveragePrecursorMz());
        res.setMaxRatio(repoCluster.getMaxPeptideRatio());

        return res;
    }

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
