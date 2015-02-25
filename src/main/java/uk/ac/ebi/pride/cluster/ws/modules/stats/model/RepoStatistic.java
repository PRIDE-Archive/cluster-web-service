package uk.ac.ebi.pride.cluster.ws.modules.stats.model;

/**
 * ClusterRepoStatistic represents a single key-value pair of stats for the whole repository
 *
 * e.g. number of clusters : 100000
 *
 * @author Rui Wang
 * @version $Id$
 */
public class RepoStatistic {
    private String name;
    private String value;

    public RepoStatistic(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
