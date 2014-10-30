package uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.model;

import uk.ac.ebi.pride.cluster.ws.modules.psmsummary.model.PsmSummary;
import uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.model.SpectrumSummary;

import java.util.Collection;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class ClusterDetail {
    int id;

    double averagePrecursorMz;

    double averagePrecursorCharge;

    int numberOfSpectra;

    double maxRatio;

    Collection<SpectrumSummary> spectra;

    Collection<PsmSummary> psms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
