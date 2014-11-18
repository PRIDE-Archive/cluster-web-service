package uk.ac.ebi.pride.cluster.ws.modules.assaysummary.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class SpeciesDistribution {


    private Map<String, SpeciesCount> distribution = new HashMap<String, SpeciesCount>();

    public Map<String, SpeciesCount> getDistribution() {
        return distribution;
    }

    public void setDistribution(Map<String, SpeciesCount> distribution) {
        this.distribution = distribution;
    }
}
