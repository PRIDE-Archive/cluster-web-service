package uk.ac.ebi.pride.cluster.ws.modules.assay.model;

/**
 * Count for a particular PTM
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ModificationCount {
    private String modificationName;
    private String modificationAccession;
    private long count;

    public ModificationCount(String modificationName, String modificationAccession, long count) {
        this.modificationName = modificationName;
        this.modificationAccession = modificationAccession;
        this.count = count;
    }

    public String getModificationName() {
        return modificationName;
    }

    public void setModificationName(String modificationName) {
        this.modificationName = modificationName;
    }

    public String getModificationAccession() {
        return modificationAccession;
    }

    public void setModificationAccession(String modificationAccession) {
        this.modificationAccession = modificationAccession;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void addModificationCount(long n) {
        this.count += n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModificationCount)) return false;

        ModificationCount modificationCount = (ModificationCount) o;

        if (count != modificationCount.count) return false;
        if (modificationAccession != null ? !modificationAccession.equals(modificationCount.modificationAccession) : modificationCount.modificationAccession != null)
            return false;
        if (modificationName != null ? !modificationName.equals(modificationCount.modificationName) : modificationCount.modificationName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = modificationName != null ? modificationName.hashCode() : 0;
        result = 31 * result + (modificationAccession != null ? modificationAccession.hashCode() : 0);
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PTMCount{" +
                "modificationName='" + modificationName + '\'' +
                ", modificationAccession='" + modificationAccession + '\'' +
                ", count=" + count +
                '}';
    }
}
