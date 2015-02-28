package uk.ac.ebi.pride.cluster.ws.modules.stats.util;

import uk.ac.ebi.pride.cluster.ws.modules.stats.model.PSMDeltaMZStatistics;
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.SpectrumSimilarityStatistics;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredSpectrumDetail;

/**
 * Collect statistics for a given cluster
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class ClusterStatsCollector {


    public static PSMDeltaMZStatistics collectPSMDeltaMZStatistics(ClusterDetail cluster) {
        PSMDeltaMZStatistics psmDeltaMZStatistics = new PSMDeltaMZStatistics();

        for (ClusteredPSMDetail clusteredPSMDetail : cluster.getClusteredPSMDetails()) {
            float deltaMZ = clusteredPSMDetail.getPsmDetail().getDeltaMZ();
            psmDeltaMZStatistics.addClusterDeltaMz(deltaMZ);

            if (clusteredPSMDetail.getPsmRatio() < 2) {
                psmDeltaMZStatistics.addHighRatioPeptideDeltaMz(deltaMZ);
            }
        }

        return psmDeltaMZStatistics;
    }


    public static SpectrumSimilarityStatistics collectSpectrumSimilarityStatistics(ClusterDetail cluster) {
        SpectrumSimilarityStatistics spectrumSimilarityStatistics = new SpectrumSimilarityStatistics();

        for (ClusteredSpectrumDetail clusteredSpectrumDetail : cluster.getClusteredSpectrumDetails()) {
            float similarityScore = clusteredSpectrumDetail.getSimilarityScore();
            spectrumSimilarityStatistics.addClusterSpectrumSimilarity(similarityScore);

            ClusteredPSMDetail clusteredPSMDetail = cluster.getClusteredPSMDetail(clusteredSpectrumDetail.getSpectrumId());
            if (clusteredPSMDetail.getPsmRatio() < 2) {
                spectrumSimilarityStatistics.addHighRatioPeptideSpectrumSimilarity(similarityScore);
            }
        }

        return spectrumSimilarityStatistics;
    }
}
