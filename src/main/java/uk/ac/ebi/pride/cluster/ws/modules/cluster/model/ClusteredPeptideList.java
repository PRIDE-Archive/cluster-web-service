package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A list of clustered peptides
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPeptideList {

    private final List<ClusteredPeptide> clusteredPeptides = new ArrayList<ClusteredPeptide>();

    public List<ClusteredPeptide> getClusteredPeptides() {
        return this.clusteredPeptides;
    }

    public void addClusteredPeptide(ClusteredPeptide clusteredPeptide) {
        this.clusteredPeptides.add(clusteredPeptide);
    }

    public void addClusteredPeptides(Collection<ClusteredPeptide> clusteredPeptides) {
        this.clusteredPeptides.addAll(clusteredPeptides);
    }
}
