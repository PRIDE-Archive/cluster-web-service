package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

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
    private final List<Float> highRatioPeptideSpectrumSimilarities = new ArrayList<Float>();

    // spectrum similarity for all the PSMs in the cluster
    private final List<Float> clusterSpectrumSimilarities = new ArrayList<Float>();

    public List<Float> getHighRatioPeptideSpectrumSimilarities() {
        return highRatioPeptideSpectrumSimilarities;
    }

    public void addHighRatioPeptideSpectrumSimilarity(Float deltaMz) {
        highRatioPeptideSpectrumSimilarities.add(deltaMz);
    }

    public List<Float> getClusterSpectrumSimilarities() {
        return clusterSpectrumSimilarities;
    }

    public void addClusterSpectrumSimilarity(Float deltaMz) {
        clusterSpectrumSimilarities.add(deltaMz);
    }
}
