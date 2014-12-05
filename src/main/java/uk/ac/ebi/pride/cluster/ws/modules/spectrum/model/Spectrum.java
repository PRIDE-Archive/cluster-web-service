package uk.ac.ebi.pride.cluster.ws.modules.spectrum.model;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class Spectrum {

    private long clusterId;

    private double mzStart;

    private double mzStop;

    private java.util.List<SpectrumPeak> peaks;

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public double getMzStart() {
        return mzStart;
    }

    public void setMzStart(double mzStart) {
        this.mzStart = mzStart;
    }

    public double getMzStop() {
        return mzStop;
    }

    public void setMzStop(double mzStop) {
        this.mzStop = mzStop;
    }

    public List<SpectrumPeak> getPeaks() {
        return peaks;
    }

    public void setPeaks(List<SpectrumPeak> peaks) {
        this.peaks = peaks;
    }
}
