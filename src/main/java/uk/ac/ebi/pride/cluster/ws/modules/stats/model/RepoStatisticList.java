package uk.ac.ebi.pride.cluster.ws.modules.stats.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of RepoStatistics
 *
 * @author Rui Wang
 * @version $Id$
 */
public class RepoStatisticList {

    private final List<RepoStatistic> repoStatistics = new ArrayList<RepoStatistic>();

    public List<RepoStatistic> getRepoStatistics() {
        return repoStatistics;
    }

    public void addRepoStatistic(RepoStatistic repoStatistic) {
        repoStatistics.add(repoStatistic);
    }
}
