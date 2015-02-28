package uk.ac.ebi.pride.cluster.ws.modules.assay.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Distribution of PTM counts
 *
 * @author Rui Wang
 * @version $Id$
 */
public class PTMDistribution {

    public static final String NO_MODIFICATIONS = "No Modifications";

    private Map<String, PTMCount> distribution = new HashMap<String, PTMCount>();

    public Map<String, PTMCount> getDistribution() {
        return distribution;
    }

    public void setDistribution(Map<String, PTMCount> distribution) {
        this.distribution = distribution;
    }
}
