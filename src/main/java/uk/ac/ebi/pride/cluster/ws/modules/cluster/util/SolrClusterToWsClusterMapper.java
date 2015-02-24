package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import org.springframework.data.domain.Page;
import uk.ac.ebi.pride.cluster.search.model.SolrCluster;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.Cluster;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class SolrClusterToWsClusterMapper {

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
        newCluster.setPeptideSequence((String) solrCluster.getHighestRatioPepSequences().toArray()[0]);
        newCluster.setClusterQuality(solrCluster.getClusterQuality().toString());
        newCluster.setProteinAccession((String) solrCluster.getHighestRatioProteinAccessions().toArray()[0]);

        //ToDo: to provide proper implementation, right now it hardcoded value
        newCluster.setNumberOfSpecies(0);
        newCluster.setNumberOfPTMs(0);
        newCluster.setNumberOfProjects(0);

        return newCluster;
    }
}
