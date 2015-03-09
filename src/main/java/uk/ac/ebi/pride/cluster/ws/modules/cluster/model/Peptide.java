package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import uk.ac.ebi.pride.cluster.ws.modules.psm.model.Modification;

import java.util.List;

/**
 * Peptide represents a unique peptide sequence plus modifications
 *
 * @author Rui Wang
 * @version $Id$
 */
public class Peptide {
    private String sequence;
    private List<Modification> modifications;


    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public List<Modification> getModifications() {
        return modifications;
    }

    public void setModifications(List<Modification> modifications) {
        this.modifications = modifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peptide)) return false;

        Peptide peptide = (Peptide) o;

        if (modifications != null ? !modifications.equals(peptide.modifications) : peptide.modifications != null)
            return false;
        if (sequence != null ? !sequence.equals(peptide.sequence) : peptide.sequence != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sequence != null ? sequence.hashCode() : 0;
        result = 31 * result + (modifications != null ? modifications.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Peptide{" +
                "sequence='" + sequence + '\'' +
                ", modifications=" + modifications +
                '}';
    }
}
