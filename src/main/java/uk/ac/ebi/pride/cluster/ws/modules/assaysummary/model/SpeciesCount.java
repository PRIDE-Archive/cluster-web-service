package uk.ac.ebi.pride.cluster.ws.modules.assaysummary.model;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class SpeciesCount {
    private String speciesName;
    private long speciesCount;

    public SpeciesCount(String speciesName, long speciesCount) {
        this.speciesName = speciesName;
        this.speciesCount = speciesCount;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public long getSpeciesCount() {
        return speciesCount;
    }

    public void setSpeciesCount(long speciesCount) {
        this.speciesCount = speciesCount;
    }


    public void addSpeciesCount(long n) {
        this.speciesCount += n;
    }
}
