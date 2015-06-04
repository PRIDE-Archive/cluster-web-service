package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusterModificationCounts;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusterSpeciesCounts;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.PSMDeltaMZStatistics;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.SpectrumSimilarityStatistics;
import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredSpectrumDetail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Collect statistics for a given cluster
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class ClusterStatsCollector {

    /**
     * Collect the species distribution for all the PSMs of a cluster
     *
     * @param cluster a given cluster
     * @return species distribution for all the PSMs
     */
    public static ClusterSpeciesCounts collectClusterSpeciesCounts(ClusterDetail cluster) {
        // Get the clustered psm details for a given cluster
        List<ClusteredPSMDetail> clusteredPSMDetails = cluster.getClusteredPSMDetails();

        // Extract the species
        SpeciesDistribution species = new SpeciesDistribution();

        for (ClusteredPSMDetail clusteredPSMDetail : clusteredPSMDetails) {
            // get assay
            Long assayId = clusteredPSMDetail.getPsmDetail().getAssayId();
            AssayDetail assayDetail = cluster.getAssayDetail(assayId);

            // count species
            for (String aSpecies : assayDetail.getSpeciesEntries()) {
                if (species.getDistribution().containsKey(aSpecies)) {
                    species.getDistribution().get(aSpecies).addSpeciesCount(1);
                } else {
                    SpeciesCount newSpeciesCount = new SpeciesCount(aSpecies, 1);
                    species.getDistribution().put(aSpecies, newSpeciesCount);
                }
            }
        }

        ClusterSpeciesCounts res = new ClusterSpeciesCounts();
        res.setSpeciesCounts(new ArrayList<SpeciesCount>(species.getDistribution().values()));
        return res;
    }

    /**
     * Collect the modification distribution for all the PSMs of a cluster
     *
     * @param cluster a given cluster
     * @return modification distribution for all the PSMs
     */
    public static ClusterModificationCounts collectClusterModificationCounts(ClusterDetail cluster) {
        // Get the clustered psm details for a given cluster
        List<ClusteredPSMDetail> clusteredPSMDetails = cluster.getClusteredPSMDetails();

        // Extract the PTMs
        ModificationDistribution modificationDistribution = new ModificationDistribution();
        for (ClusteredPSMDetail clusteredPSMDetail : clusteredPSMDetails) {
            //todo: change to use standardised mods
            List<ModificationProvider> mods = clusteredPSMDetail.getPsmDetail().getModifications();

            if (mods.isEmpty()) {
                if (modificationDistribution.getDistribution().containsKey(ModificationDistribution.NO_MODIFICATIONS)) {
                    modificationDistribution.getDistribution().get(ModificationDistribution.NO_MODIFICATIONS).addModificationCount(1);
                } else {
                    ModificationCount modificationCount = new ModificationCount(ModificationDistribution.NO_MODIFICATIONS, null, 1);
                    modificationDistribution.getDistribution().put(ModificationDistribution.NO_MODIFICATIONS, modificationCount);
                }
            } else {
                Set<String> countedModAccession = new HashSet<String>();
                for (ModificationProvider modification : mods) {
                    String accession = modification.getAccession();
                    if (!countedModAccession.contains(accession)) {
                        if (modificationDistribution.getDistribution().containsKey(accession)) {
                            modificationDistribution.getDistribution().get(accession).addModificationCount(1);
                        } else {
                            String name = modification.getName();
                            if (name == null) {
                                name = accession;
                            }
                            ModificationCount modificationCount = new ModificationCount(name, accession, 1);
                            modificationDistribution.getDistribution().put(accession, modificationCount);
                        }
                        countedModAccession.add(accession);
                    }
                }
            }
        }

        ClusterModificationCounts res = new ClusterModificationCounts();
        res.setModificationCounts(new ArrayList<ModificationCount>(modificationDistribution.getDistribution().values()));
        return res;
    }

    /**
     * Collect delta m/z distributions of all the PSMs for a cluster
     *
     * @param cluster a given cluster
     * @return PSM's delta m/z distributions
     */
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

    /**
     * Collect spectrum similarity distributions of all the spectra in a cluster
     *
     * @param cluster a given cluster
     * @return Spectra's similarity distribution
     */
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
