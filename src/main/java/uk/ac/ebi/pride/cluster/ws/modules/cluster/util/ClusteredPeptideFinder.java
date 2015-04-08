package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPeptide;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPeptideList;
import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Find clustered peptides for a given cluster
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class ClusteredPeptideFinder {

    public static ClusteredPeptideList findClusteredPeptides(ClusterDetail cluster) {

        ClusteredPeptideList clusteredPeptideList = new ClusteredPeptideList();

        if (cluster != null) {

            Map<String, ClusteredPeptide> clusteredPeptideMap = new HashMap<String, ClusteredPeptide>();
            for (ClusteredPSMDetail clusteredPSMDetail : cluster.getClusteredPSMDetails()) {
                String sequence = clusteredPSMDetail.getSequence();
                String seqModCombined = sequence + clusteredPSMDetail.getModifications();

                ClusteredPeptide clusteredPeptide = clusteredPeptideMap.get(seqModCombined);
                if (clusteredPeptide == null) {
                    clusteredPeptide = new ClusteredPeptide();
                    clusteredPeptide.setClusterId(cluster.getId());
                    // sequence
                    clusteredPeptide.setSequence(sequence);
                    // modifications
                    clusteredPeptide.setModifications(clusteredPSMDetail.getPsmDetail().getModifications());
                    clusteredPeptideMap.put(seqModCombined, clusteredPeptide);
                }

                // PSM count
                clusteredPeptide.addPSMCount(1);

                // species
                AssayDetail assayDetail = cluster.getAssayDetail(clusteredPSMDetail.getPsmDetail().getAssayId());
                Set<String> taxonomyIdEntries = assayDetail.getTaxonomyIdEntries();
                clusteredPeptide.addSpecies(taxonomyIdEntries);

                // project accession
                String projectAccession = assayDetail.getProjectAccession();
                clusteredPeptide.addProjectAccession(projectAccession);
            }

            clusteredPeptideList.addClusteredPeptides(clusteredPeptideMap.values());
        }

        return clusteredPeptideList;
    }
}
