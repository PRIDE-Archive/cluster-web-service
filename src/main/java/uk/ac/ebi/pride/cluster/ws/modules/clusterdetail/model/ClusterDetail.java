package uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.model;

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
}
