package uk.ac.ebi.pride.cluster.ws.modules.cluster.filter;

import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

/**
 * Filter out PSM that has modifications
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPSMNoModificationFilter implements IPredicate<ClusteredPSMDetail>{

    @Override
    public boolean apply(ClusteredPSMDetail psm) {
        return psm.getPsmDetail().getModificationString() == null;
    }
}
