package uk.ac.ebi.pride.cluster.ws.modules.psm.model;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class Psm {
    private Long id;
    private Long clusterId;
    private String sequence;
    private float ratio;
    private Long spectrumId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    public Long getClusterId() {
        return clusterId;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSequence() {
        return sequence;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public float getRatio() {
        return ratio;
    }

    public void setSpectrumId(Long spectrumId) {
        this.spectrumId = spectrumId;
    }

    public Long getSpectrumId() {
        return spectrumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Psm)) return false;

        Psm psm = (Psm) o;

        if (Float.compare(psm.ratio, ratio) != 0) return false;
        if (clusterId != null ? !clusterId.equals(psm.clusterId) : psm.clusterId != null) return false;
        if (id != null ? !id.equals(psm.id) : psm.id != null) return false;
        if (sequence != null ? !sequence.equals(psm.sequence) : psm.sequence != null) return false;
        if (spectrumId != null ? !spectrumId.equals(psm.spectrumId) : psm.spectrumId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (clusterId != null ? clusterId.hashCode() : 0);
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        result = 31 * result + (ratio != +0.0f ? Float.floatToIntBits(ratio) : 0);
        result = 31 * result + (spectrumId != null ? spectrumId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Psm{" +
                "id=" + id +
                ", clusterId=" + clusterId +
                ", sequence='" + sequence + '\'' +
                ", ratio=" + ratio +
                ", spectrumId=" + spectrumId +
                '}';
    }
}
