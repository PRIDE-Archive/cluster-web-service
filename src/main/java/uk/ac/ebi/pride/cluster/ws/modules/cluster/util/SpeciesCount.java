package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 */
public class SpeciesCount {
    private String speciesName;
    private long count;

    public SpeciesCount(String speciesName, long count) {
        this.speciesName = speciesName;
        this.count = count;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    public void addSpeciesCount(long n) {
        this.count += n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesCount)) return false;

        SpeciesCount that = (SpeciesCount) o;

        if (count != that.count) return false;
        if (speciesName != null ? !speciesName.equals(that.speciesName) : that.speciesName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = speciesName != null ? speciesName.hashCode() : 0;
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SpeciesCount{" +
                "speciesName='" + speciesName + '\'' +
                ", count=" + count +
                '}';
    }
}
