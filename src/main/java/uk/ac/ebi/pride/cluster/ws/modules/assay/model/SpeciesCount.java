package uk.ac.ebi.pride.cluster.ws.modules.assay.model;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesCount)) return false;

        SpeciesCount that = (SpeciesCount) o;

        if (speciesCount != that.speciesCount) return false;
        if (speciesName != null ? !speciesName.equals(that.speciesName) : that.speciesName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = speciesName != null ? speciesName.hashCode() : 0;
        result = 31 * result + (int) (speciesCount ^ (speciesCount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SpeciesCount{" +
                "speciesName='" + speciesName + '\'' +
                ", speciesCount=" + speciesCount +
                '}';
    }
}
