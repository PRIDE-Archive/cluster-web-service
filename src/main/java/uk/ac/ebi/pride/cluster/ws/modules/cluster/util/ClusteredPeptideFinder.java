package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPeptide;
import uk.ac.ebi.pride.cluster.ws.modules.psm.model.PTM;
import uk.ac.ebi.pride.cluster.ws.modules.psm.util.RepoPTMToWsPTMMapper;
import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.PTMDetail;

import java.util.*;

/**
 * Find clustered peptides for a given cluster
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class ClusteredPeptideFinder {

    public static List<ClusteredPeptide> findClusteredPeptides(ClusterDetail cluster) {

        if (cluster != null) {
            List<ClusteredPSMDetail> clusteredPSMDetails = cluster.getClusteredPSMDetails();

            Map<String, ClusteredPeptide> clusteredPeptideMap = new HashMap<String, ClusteredPeptide>();
            for (ClusteredPSMDetail clusteredPSMDetail : clusteredPSMDetails) {
                String sequence = clusteredPSMDetail.getSequence();
                String seqModCombined = sequence + clusteredPSMDetail.getModifications();

                ClusteredPeptide clusteredPeptide = clusteredPeptideMap.get(seqModCombined);
                if (clusteredPeptide == null) {
                    clusteredPeptide = new ClusteredPeptide();
                    clusteredPeptide.setClusterId(cluster.getId());
                    // sequence
                    clusteredPeptide.setSequence(sequence);
                    // ptm
                    List<PTMDetail> modifications = clusteredPSMDetail.getPsmDetail().getModifications();
                    List<PTM> ptms = RepoPTMToWsPTMMapper.asPTMList(modifications);
                    clusteredPeptide.setModifications(ptms);

                    clusteredPeptideMap.put(seqModCombined, clusteredPeptide);
                }

                // PSM count
                clusteredPeptide.addPSMCount(1);

                // species
                AssayDetail assayDetail = cluster.getAssayDetail(clusteredPSMDetail.getPsmDetail().getAssayId());
                Set<String> taxonomyIdEntries = assayDetail.getTaxonomyIdEntries();
                clusteredPeptide.addSpecies(taxonomyIdEntries);
            }

            return new ArrayList<ClusteredPeptide>(clusteredPeptideMap.values());
        } else {
            return Collections.emptyList();
        }
    }
}
