package uk.ac.ebi.pride.cluster.ws.modules.assaysummary.model;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class Species {
    private String speciesName;
    private String speciesAccession;

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getSpeciesAccession() {
        return speciesAccession;
    }

    public void setSpeciesAccession(String speciesAccession) {
        this.speciesAccession = speciesAccession;
    }
}
