package uk.ac.ebi.pride.cluster.ws.modules.stats.util;

import uk.ac.ebi.pride.cluster.ws.modules.stats.model.RepoStatistic;
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.RepoStatisticList;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterRepoStatistics;

import java.util.List;

/**
 * Mapper class maps the statistics from repo level to the web service level
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class RepoStatsToWsStatsMapper {

    public static RepoStatisticList asStatsList(List<ClusterRepoStatistics> repoStats) {
        RepoStatisticList repoStatisticList = new RepoStatisticList();

        for (ClusterRepoStatistics repoStat : repoStats) {
            repoStatisticList.addRepoStatistic(new RepoStatistic(repoStat.getName(), repoStat.getValue()));
        }

        return repoStatisticList;
    }
}
