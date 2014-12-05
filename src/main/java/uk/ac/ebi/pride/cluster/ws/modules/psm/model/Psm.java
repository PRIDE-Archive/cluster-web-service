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
}
