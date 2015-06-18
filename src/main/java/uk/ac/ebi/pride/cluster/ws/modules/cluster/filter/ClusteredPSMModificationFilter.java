package uk.ac.ebi.pride.cluster.ws.modules.cluster.filter;

import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

/**
 * Filter by modification
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPSMModificationFilter implements IPredicate<ClusteredPSMDetail> {

    private final String modification;

    public ClusteredPSMModificationFilter(String modification) {
        this.modification = modification;
    }

    @Override
    public boolean apply(ClusteredPSMDetail psm) {
        String clusteredPSMDetailModifications = psm.getPsmDetail().getModificationString();
        return modification.equalsIgnoreCase(clusteredPSMDetailModifications);

    }
}
