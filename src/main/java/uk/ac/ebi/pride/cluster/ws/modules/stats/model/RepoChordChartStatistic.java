package uk.ac.ebi.pride.cluster.ws.modules.stats.model;

/**
 * @author Rui Wang
 * @version $Id$
 */
public class RepoChordChartStatistic {
    private String[] labels;
    private Long[][] counts;

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public Long[][] getCounts() {
        return counts;
    }

    public void setCounts(Long[][] counts) {
        this.counts = counts;
    }
}
