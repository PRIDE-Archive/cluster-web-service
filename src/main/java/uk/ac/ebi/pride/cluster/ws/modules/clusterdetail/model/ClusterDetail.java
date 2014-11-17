package uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.model;

import uk.ac.ebi.pride.cluster.ws.modules.psmsummary.model.PsmSummary;
import uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.model.SpectrumSummary;

import java.util.Collection;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class ClusterDetail {

    private long id;

    private double averagePrecursorMz;

    private double averagePrecursorCharge;

    private int numberOfSpectra;

    private double maxRatio;

    private String consensusSpectrumMz;

    private String consensusSpectrumIntensity;

    private Collection<SpectrumSummary> spectra;

    private Collection<PsmSummary> psms;

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

    public int getNumberOfSpectra() {
        return numberOfSpectra;
    }

    public void setNumberOfSpectra(int numberOfSpectra) {
        this.numberOfSpectra = numberOfSpectra;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    public String getConsensusSpectrumMz() {
        return consensusSpectrumMz;
    }

    public void setConsensusSpectrumMz(String consensusSpectrumMz) {
        this.consensusSpectrumMz = consensusSpectrumMz;
    }

    public String getConsensusSpectrumIntensity() {
        return consensusSpectrumIntensity;
    }

    public void setConsensusSpectrumIntensity(String consensusSpectrumIntensity) {
        this.consensusSpectrumIntensity = consensusSpectrumIntensity;
    }

    public Collection<SpectrumSummary> getSpectra() {
        return spectra;
    }

    public void setSpectra(Collection<SpectrumSummary> spectra) {
        this.spectra = spectra;
    }

    public Collection<PsmSummary> getPsms() {
        return psms;
    }

    public void setPsms(Collection<PsmSummary> psms) {
        this.psms = psms;
    }

}
