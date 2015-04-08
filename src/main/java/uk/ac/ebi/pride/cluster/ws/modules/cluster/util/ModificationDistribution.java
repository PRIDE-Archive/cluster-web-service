package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Distribution of PTM counts
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ModificationDistribution {

    public static final String NO_MODIFICATIONS = "No Modifications";

    private Map<String, ModificationCount> distribution = new HashMap<String, ModificationCount>();

    public Map<String, ModificationCount> getDistribution() {
        return distribution;
    }

    public void setDistribution(Map<String, ModificationCount> distribution) {
        this.distribution = distribution;
    }
}
