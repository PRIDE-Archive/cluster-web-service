package uk.ac.ebi.pride.cluster.ws.modules.stats.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Statistics
 *
 * @author Rui Wang
 * @version $Id$
 */
public class SpectrumSimilarityStatistics {
    // spectrum similarity to the consensus spectrum for the PSMs with the highest ratio peptide sequence in the cluster
    private final List<Float> highRatioPeptideSpectrumSimilarity = new ArrayList<Float>();

    // spectrum similarity for all the PSMs in the cluster
    private final List<Float> clusterSpectrumSimilarity = new ArrayList<Float>();

    public List<Float> getHighRatioPeptideSpectrumSimilarity() {
        return highRatioPeptideSpectrumSimilarity;
    }

    public void addHighRatioPeptideSpectrumSimilarity(Float deltaMz) {
        highRatioPeptideSpectrumSimilarity.add(deltaMz);
    }

    public List<Float> getClusterSpectrumSimilarity() {
        return clusterSpectrumSimilarity;
    }

    public void addClusterSpectrumSimilarity(Float deltaMz) {
        clusterSpectrumSimilarity.add(deltaMz);
    }
}
