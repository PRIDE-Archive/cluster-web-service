package uk.ac.ebi.pride.cluster.ws.modules.clustersummary.util;

import org.springframework.data.domain.Page;
import uk.ac.ebi.pride.cluster.search.model.SolrCluster;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.model.ClusterSummary;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class SolrClusterToWsClusterSummaryMapper {

    public static List<ClusterSummary> asClusterSummaryList(Page<SolrCluster> solrClusterPage) {
        List<ClusterSummary> res = new LinkedList<ClusterSummary>();

        for (SolrCluster solrCluster: solrClusterPage.getContent()) {
            ClusterSummary newClusterSummary = new ClusterSummary();

            newClusterSummary.setId(solrCluster.getId());
            newClusterSummary.setNumberOfSpectra(solrCluster.getNumberOfSpectra());
            newClusterSummary.setAveragePrecursorCharge(solrCluster.getAveragePrecursorCharge());
            newClusterSummary.setAveragePrecursorMz(solrCluster.getAveragePrecursorMz());
            newClusterSummary.setMaxRatio(solrCluster.getMaxRatio());

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

}
