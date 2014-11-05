package uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.model;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class Spectrum {

    private long id;

    private double mzStart;

    private double mzStop;

    private java.util.List<SpectrumPeak> peaks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
