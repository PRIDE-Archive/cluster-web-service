package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.cluster.ws.modules.cluster.filter.IPredicate;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPSM;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPSMList;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPeptide;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPeptideList;
import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

import java.util.*;

/**
 * Find clustered peptides or psms for a given cluster
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
                String seqModCombined = sequence + clusteredPSMDetail.getPsmDetail().getModificationString();

                ClusteredPeptide clusteredPeptide = clusteredPeptideMap.get(seqModCombined);
                if (clusteredPeptide == null) {
                    clusteredPeptide = new ClusteredPeptide();
                    clusteredPeptide.setClusterId(cluster.getId());

                    // sequence
                    clusteredPeptide.setSequence(sequence);

                    // modifications
                    clusteredPeptide.setModifications(clusteredPSMDetail.getPsmDetail().getModifications());
                    clusteredPeptideMap.put(seqModCombined, clusteredPeptide);

                    // whether it is consensus peptide
                    clusteredPeptide.setConsensusPeptide(clusteredPSMDetail.getRank() == 1.1f);
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

    public static ClusteredPSMList findClusteredPSMs(ClusterDetail cluster, IPredicate<ClusteredPSMDetail> psmFilter,
                                                     int pageNo, int pageSize) {
        ClusteredPSMList clusteredPSMList = new ClusteredPSMList();

        // filter clustered psms by peptide sequence
        List<ClusteredPSMDetail> clusteredPSMDetails = new ArrayList<ClusteredPSMDetail>(cluster.getClusteredPSMDetails());

        // filter clustered psms by project accession and modifications
        Iterator<ClusteredPSMDetail> clusteredPSMDetailIterator = clusteredPSMDetails.iterator();
        while(clusteredPSMDetailIterator.hasNext()) {
            ClusteredPSMDetail clusteredPSMDetail = clusteredPSMDetailIterator.next();

            if (!psmFilter.apply(clusteredPSMDetail)) {
                clusteredPSMDetailIterator.remove();
            }
        }

        // pagination
        List<ClusteredPSMDetail> results = new ArrayList<ClusteredPSMDetail>();

        int totalPagedSize = (pageNo + 1) * pageSize;

        if (totalPagedSize > 0) {
            int lastIndex = totalPagedSize >= clusteredPSMDetails.size() ? clusteredPSMDetails.size() : totalPagedSize;
            results.addAll(clusteredPSMDetails.subList(pageNo * pageSize, lastIndex));

            List<ClusteredPSM> clusteredPSMs = RepoPSMToWsPSMMapper.asPSMList(results, cluster);
            clusteredPSMList.setPsms(clusteredPSMs);
        }

        clusteredPSMList.setTotalResults(clusteredPSMDetails.size());
        clusteredPSMList.setPageNumber(pageNo);
        clusteredPSMList.setPageSize(pageSize);

        return clusteredPSMList;
    }
}
