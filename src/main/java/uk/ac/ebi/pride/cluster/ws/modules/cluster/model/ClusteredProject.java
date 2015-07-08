package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import java.util.Set;

/**
 * PRIDE Archive projects that have been clustered
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredProject {

    private String accession;

    private String title;

    private int numberOfPSMs = 0;

    private Set<String> species;

    private Set<String> diseases;

    private Set<String> tissues;

    private Set<String> instruments;

    private Set<String> searchEngines;

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPSMs() {
        return numberOfPSMs;
    }

    public void setNumberOfPSMs(int numberOfPSMs) {
        this.numberOfPSMs = numberOfPSMs;
    }

    public void incrementNumberOfPSMs(int increment) {
        this.numberOfPSMs += increment;
    }

    public Set<String> getSpecies() {
        return species;
    }

    public void setSpecies(Set<String> species) {
        this.species = species;
    }

    public Set<String> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<String> diseases) {
        this.diseases = diseases;
    }

    public Set<String> getTissues() {
        return tissues;
    }

    public void setTissues(Set<String> tissues) {
        this.tissues = tissues;
    }

    public Set<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<String> instruments) {
        this.instruments = instruments;
    }

    public Set<String> getSearchEngines() {
        return searchEngines;
    }

    public void setSearchEngines(Set<String> searchEngines) {
        this.searchEngines = searchEngines;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClusteredProject)) return false;

        ClusteredProject that = (ClusteredProject) o;

        if (numberOfPSMs != that.numberOfPSMs) return false;
        if (accession != null ? !accession.equals(that.accession) : that.accession != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (species != null ? !species.equals(that.species) : that.species != null) return false;
        if (diseases != null ? !diseases.equals(that.diseases) : that.diseases != null) return false;
        if (tissues != null ? !tissues.equals(that.tissues) : that.tissues != null) return false;
        if (instruments != null ? !instruments.equals(that.instruments) : that.instruments != null) return false;
        return !(searchEngines != null ? !searchEngines.equals(that.searchEngines) : that.searchEngines != null);

    }

    @Override
    public int hashCode() {
        int result = accession != null ? accession.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + numberOfPSMs;
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (diseases != null ? diseases.hashCode() : 0);
        result = 31 * result + (tissues != null ? tissues.hashCode() : 0);
        result = 31 * result + (instruments != null ? instruments.hashCode() : 0);
        result = 31 * result + (searchEngines != null ? searchEngines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClusteredProject{" +
                "accession='" + accession + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPSMs=" + numberOfPSMs +
                ", species=" + species +
                ", diseases=" + diseases +
                ", tissues=" + tissues +
                ", instruments=" + instruments +
                ", searchEngines=" + searchEngines +
                '}';
    }
}
