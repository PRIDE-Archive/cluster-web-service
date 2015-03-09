package uk.ac.ebi.pride.cluster.ws.modules.psm.model;

/**
 * Post translational modifications
 *
 * @author Rui Wang
 * @version $Id$
 */
public class Modification {
    private String accession;
    private String name;
    private int position;

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modification)) return false;

        Modification modification = (Modification) o;

        if (position != modification.position) return false;
        if (accession != null ? !accession.equals(modification.accession) : modification.accession != null) return false;
        if (name != null ? !name.equals(modification.name) : modification.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accession != null ? accession.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + position;
        return result;
    }

    @Override
    public String toString() {
        return "PTM{" +
                "accession='" + accession + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
