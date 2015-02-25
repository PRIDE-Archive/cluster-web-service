package uk.ac.ebi.pride.cluster.ws.modules.assay.model;

/**
 * Count for a particular PTM
 *
 * @author Rui Wang
 * @version $Id$
 */
public class PTMCount {
    private String ptmName;
    private String ptmAccession;
    private long count;

    public PTMCount(String ptmName, String ptmAccession, long count) {
        this.ptmName = ptmName;
        this.ptmAccession = ptmAccession;
        this.count = count;
    }

    public String getPtmName() {
        return ptmName;
    }

    public void setPtmName(String ptmName) {
        this.ptmName = ptmName;
    }

    public String getPtmAccession() {
        return ptmAccession;
    }

    public void setPtmAccession(String ptmAccession) {
        this.ptmAccession = ptmAccession;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void addPTMCount(long n) {
        this.count += n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PTMCount)) return false;

        PTMCount ptmCount = (PTMCount) o;

        if (count != ptmCount.count) return false;
        if (ptmAccession != null ? !ptmAccession.equals(ptmCount.ptmAccession) : ptmCount.ptmAccession != null)
            return false;
        if (ptmName != null ? !ptmName.equals(ptmCount.ptmName) : ptmCount.ptmName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ptmName != null ? ptmName.hashCode() : 0;
        result = 31 * result + (ptmAccession != null ? ptmAccession.hashCode() : 0);
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PTMCount{" +
                "ptmName='" + ptmName + '\'' +
                ", ptmAccession='" + ptmAccession + '\'' +
                ", count=" + count +
                '}';
    }
}
