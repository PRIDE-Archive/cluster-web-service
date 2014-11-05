package uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.model;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class SpectrumPeak {

    private double mz;
    private double intensity;

    public double getMz() {
        return mz;
    }

    public void setMz(double mz) {
        this.mz = mz;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
