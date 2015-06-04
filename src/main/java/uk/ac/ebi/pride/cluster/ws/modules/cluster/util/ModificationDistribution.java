package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;

import java.util.Collection;
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

    private final Map<String, ModificationCount> accessionBasedDistribution = new HashMap<String, ModificationCount>();
    private final Map<String, ModificationCount> nameBasedDistribution = new HashMap<String, ModificationCount>();

    public Collection<ModificationCount> getDistribution() {
        return accessionBasedDistribution.values();
    }

    public void incrementModificationCount(ModificationProvider modification, int increment) {
        String name = modification.getName();
        String accession = modification.getAccession();

        incrementModificationCount(name, accession, increment);
    }

    public void incrementModificationCount(String name, String accession, int increment) {
        if (name == null) {
            name = accession;
        }

        ModificationCount modificationCount = accessionBasedDistribution.get(accession.toLowerCase());
        if (modificationCount == null) {
            modificationCount = nameBasedDistribution.get(name.toLowerCase());
        }

        if (modificationCount == null) {
            ModificationCount count = new ModificationCount(name, accession, increment);
            accessionBasedDistribution.put(accession.toLowerCase(), count);
            nameBasedDistribution.put(name.toLowerCase(), count);
        } else {
            modificationCount.addModificationCount(increment);
        }
    }
}
