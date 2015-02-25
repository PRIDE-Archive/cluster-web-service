package uk.ac.ebi.pride.cluster.ws.modules.stats.util;

import uk.ac.ebi.pride.cluster.ws.modules.stats.model.RepoStatistic;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterRepoStatistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class maps the statistics from repo level to the web service level
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class RepoStatsToWsStatsMapper {

    public static List<RepoStatistic> asStatsList(List<ClusterRepoStatistics> repoStats) {
        List<RepoStatistic> repoStatistics = new ArrayList<RepoStatistic>();

        for (ClusterRepoStatistics repoStat : repoStats) {
            repoStatistics.add(new RepoStatistic(repoStat.getName(), repoStat.getValue()));
        }

        return repoStatistics;
    }
}
