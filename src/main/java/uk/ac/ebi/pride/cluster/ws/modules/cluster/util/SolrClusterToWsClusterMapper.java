package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import org.springframework.data.domain.Page;
import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;
import uk.ac.ebi.pride.cluster.search.model.SolrCluster;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.Cluster;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public final class SolrClusterToWsClusterMapper {

    public static List<Cluster> asClusterList(Page<SolrCluster> solrClusterPage) {
        List<Cluster> res = new LinkedList<Cluster>();

        for (SolrCluster solrCluster: solrClusterPage.getContent()) {
            res.add(asCluster(solrCluster));
        }

        return res;
    }

    public static Cluster asCluster(SolrCluster solrCluster) {
        Cluster newCluster = new Cluster();

        newCluster.setId(solrCluster.getId());
        newCluster.setNumberOfSpectra(solrCluster.getNumberOfSpectra());
        newCluster.setAveragePrecursorCharge(solrCluster.getAveragePrecursorCharge());
        newCluster.setAveragePrecursorMz(solrCluster.getAveragePrecursorMz());
        newCluster.setMaxRatio(solrCluster.getMaxRatio());
        newCluster.setSequence((String) solrCluster.getHighestRatioPepSequences().toArray()[0]);
        newCluster.setClusterQuality(solrCluster.getClusterQuality());
        newCluster.setProteinAccession((String) solrCluster.getHighestRatioProteinAccessions().toArray()[0]);

        // counts
        newCluster.setNumberOfSpectra(solrCluster.getNumberOfSpectra());
        newCluster.setTotalNumberOfSpectra(solrCluster.getTotalNumberOfSpectra());
        newCluster.setNumberOfSpecies(solrCluster.getNumberOfSpecies());
        newCluster.setTotalNumberOfSpecies(solrCluster.getTotalNumberOfSpecies());
        newCluster.setNumberOfModifications(solrCluster.getNumberOfModifications());
        newCluster.setTotalNumberOfModifications(solrCluster.getTotalNumberOfModifications());
        newCluster.setNumberOfProjects(solrCluster.getNumberOfProjects());
        newCluster.setTotalNumberOfProjects(solrCluster.getTotalNumberOfProjects());

        // modifications
        Iterable<ModificationProvider> modifications = solrCluster.getModifications();
        if (modifications != null) {
            for (ModificationProvider modification : modifications) {
                newCluster.addModification(modification);
            }
        }

        return newCluster;
    }
}
