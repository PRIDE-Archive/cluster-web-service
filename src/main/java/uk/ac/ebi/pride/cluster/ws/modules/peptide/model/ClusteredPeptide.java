package uk.ac.ebi.pride.cluster.ws.modules.peptide.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Peptide summaries PSMs grouped by a cluster
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPeptide extends Peptide {

    // cluster id
    private long clusterId;

    // Number of PSMs have identify this peptide for a given cluster
    private int numberOfPSMs = 0;

    // species in taxonomy ids have been assigned to this peptide for a given cluster
    private final Set<String> species = new LinkedHashSet<String>();

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public int getNumberOfPSMs() {
        return numberOfPSMs;
    }

    public void addPSMCount(int increment) {
        numberOfPSMs += increment;
    }

    public void setNumberOfPSMs(int numberOfPSMs) {
        this.numberOfPSMs = numberOfPSMs;
    }

    public Set<String> getSpecies() {
        return species;
    }

    public void addSpecies(String taxonomyId) {
        species.add(taxonomyId);
    }

    public void addSpecies(Collection<String> taxonomyIds) {
        species.addAll(taxonomyIds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClusteredPeptide)) return false;
        if (!super.equals(o)) return false;

        ClusteredPeptide that = (ClusteredPeptide) o;

        if (clusterId != that.clusterId) return false;
        if (numberOfPSMs != that.numberOfPSMs) return false;
        if (species != null ? !species.equals(that.species) : that.species != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (clusterId ^ (clusterId >>> 32));
        result = 31 * result + numberOfPSMs;
        result = 31 * result + (species != null ? species.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClusteredPeptide{" +
                "clusterId=" + clusterId +
                ", numberOfPSMs=" + numberOfPSMs +
                ", species=" + species +
                "} " + super.toString();
    }
}
