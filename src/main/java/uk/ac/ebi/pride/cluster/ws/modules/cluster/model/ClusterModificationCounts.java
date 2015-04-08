package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import uk.ac.ebi.pride.cluster.ws.modules.cluster.util.ModificationCount;

import java.util.List;

/**
 * A list of PTM counts
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusterModificationCounts {

    private List<ModificationCount> modificationCounts;

    public List<ModificationCount> getModificationCounts() {
        return modificationCounts;
    }

    public void setModificationCounts(List<ModificationCount> modificationCounts) {
        this.modificationCounts = modificationCounts;
    }
}
