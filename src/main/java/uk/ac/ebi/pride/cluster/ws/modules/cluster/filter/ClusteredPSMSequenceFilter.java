package uk.ac.ebi.pride.cluster.ws.modules.cluster.filter;

import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

/**
 * Filter by peptide sequence
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPSMSequenceFilter implements IPredicate<ClusteredPSMDetail>{

    private final String sequence;

    public ClusteredPSMSequenceFilter(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean apply(ClusteredPSMDetail psm) {
        return sequence == null || psm.getSequence().equalsIgnoreCase(sequence);
    }
}
