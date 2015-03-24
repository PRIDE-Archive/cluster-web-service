package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class Cluster {

    private long id;

    private double averagePrecursorMz;

    private double averagePrecursorCharge;

    private long numberOfSpectra;

    private long totalNumberOfSpectra;

    private long numberOfSpecies;

    private long totalNumberOfSpecies;

    private long numberOfModifications;

    private long totalNumberOfModifications;

    private long numberOfProjects;

    private long totalNumberOfProjects;

    private double maxRatio;

    private String sequence;

    private final List<ModificationProvider> modifications = new ArrayList<ModificationProvider>();

    private String proteinAccession;

    private String clusterQuality;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAveragePrecursorMz() {
        return averagePrecursorMz;
    }

    public void setAveragePrecursorMz(double averagePrecursorMz) {
        this.averagePrecursorMz = averagePrecursorMz;
    }

    public double getAveragePrecursorCharge() {
        return averagePrecursorCharge;
    }

    public void setAveragePrecursorCharge(double averagePrecursorCharge) {
        this.averagePrecursorCharge = averagePrecursorCharge;
    }

    public long getNumberOfSpectra() {
        return numberOfSpectra;
    }

    public void setNumberOfSpectra(long numberOfSpectra) {
        this.numberOfSpectra = numberOfSpectra;
    }

    public long getNumberOfSpecies() {
        return numberOfSpecies;
    }

    public void setNumberOfSpecies(long numberOfSpecies) {
        this.numberOfSpecies = numberOfSpecies;
    }

    public long getNumberOfModifications() {
        return numberOfModifications;
    }

    public void setNumberOfModifications(long numberOfMods) {
        this.numberOfModifications = numberOfMods;
    }

    public long getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(long numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String peptideSequence) {
        this.sequence = peptideSequence;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public String getClusterQuality() {
        return clusterQuality;
    }

    public void setClusterQuality(String clusterQuality) {
        this.clusterQuality = clusterQuality;
    }

    public long getTotalNumberOfSpectra() {
        return totalNumberOfSpectra;
    }

    public void setTotalNumberOfSpectra(long totalNumberOfSpectra) {
        this.totalNumberOfSpectra = totalNumberOfSpectra;
    }

    public long getTotalNumberOfSpecies() {
        return totalNumberOfSpecies;
    }

    public void setTotalNumberOfSpecies(long totalNumberOfSpecies) {
        this.totalNumberOfSpecies = totalNumberOfSpecies;
    }

    public long getTotalNumberOfModifications() {
        return totalNumberOfModifications;
    }

    public void setTotalNumberOfModifications(long totalNumberOfModifications) {
        this.totalNumberOfModifications = totalNumberOfModifications;
    }

    public long getTotalNumberOfProjects() {
        return totalNumberOfProjects;
    }

    public void setTotalNumberOfProjects(long totalNumberOfProjects) {
        this.totalNumberOfProjects = totalNumberOfProjects;
    }

    public List<ModificationProvider> getModifications() {
        return modifications;
    }

    public void addModifications(List<ModificationProvider> peptideModifications) {
        this.modifications.addAll(peptideModifications);
    }
}
