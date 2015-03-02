package uk.ac.ebi.pride.cluster.ws.modules.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Project contains some details about a PRIDE Archive project
 *
 * @author Rui Wang
 * @version $Id$
 */
public class Project {

    private String accession;
    private String title;
    private String description;
    private List<String> experimentTypes = new ArrayList<String>();
    private List<String> instruments = new ArrayList<String>();
    private List<String> species = new ArrayList<String>();
    private List<String> tissues = new ArrayList<String>();
    private List<String> tags = new ArrayList<String>();
    private long pubMedId = -1;
    private String publicationTitle;
    private String publicationAbstract;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getExperimentTypes() {
        return experimentTypes;
    }

    public void setExperimentTypes(List<String> experimentTypes) {
        this.experimentTypes = experimentTypes;
    }

    public List<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<String> instruments) {
        this.instruments = instruments;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getTissues() {
        return tissues;
    }

    public void setTissues(List<String> tissues) {
        this.tissues = tissues;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public long getPubMedId() {
        return pubMedId;
    }

    public void setPubMedId(long pubMedId) {
        this.pubMedId = pubMedId;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }

    public String getPublicationAbstract() {
        return publicationAbstract;
    }

    public void setPublicationAbstract(String publicationAbstract) {
        this.publicationAbstract = publicationAbstract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (pubMedId != project.pubMedId) return false;
        if (accession != null ? !accession.equals(project.accession) : project.accession != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (experimentTypes != null ? !experimentTypes.equals(project.experimentTypes) : project.experimentTypes != null)
            return false;
        if (instruments != null ? !instruments.equals(project.instruments) : project.instruments != null) return false;
        if (publicationAbstract != null ? !publicationAbstract.equals(project.publicationAbstract) : project.publicationAbstract != null)
            return false;
        if (publicationTitle != null ? !publicationTitle.equals(project.publicationTitle) : project.publicationTitle != null)
            return false;
        if (species != null ? !species.equals(project.species) : project.species != null) return false;
        if (tags != null ? !tags.equals(project.tags) : project.tags != null) return false;
        if (tissues != null ? !tissues.equals(project.tissues) : project.tissues != null) return false;
        if (title != null ? !title.equals(project.title) : project.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accession != null ? accession.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (experimentTypes != null ? experimentTypes.hashCode() : 0);
        result = 31 * result + (instruments != null ? instruments.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (tissues != null ? tissues.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (int) (pubMedId ^ (pubMedId >>> 32));
        result = 31 * result + (publicationTitle != null ? publicationTitle.hashCode() : 0);
        result = 31 * result + (publicationAbstract != null ? publicationAbstract.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "accession='" + accession + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", experimentTypes=" + experimentTypes +
                ", instruments=" + instruments +
                ", species=" + species +
                ", tissues=" + tissues +
                ", tags=" + tags +
                ", pubMedId=" + pubMedId +
                ", publicationTitle='" + publicationTitle + '\'' +
                ", publicationAbstract='" + publicationAbstract + '\'' +
                '}';
    }
}
