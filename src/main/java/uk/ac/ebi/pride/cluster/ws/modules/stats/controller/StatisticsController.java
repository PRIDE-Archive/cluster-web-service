package uk.ac.ebi.pride.cluster.ws.modules.stats.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.RepoStatisticList;
import uk.ac.ebi.pride.cluster.ws.modules.stats.util.RepoStatsToWsStatsMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.cluster.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.dao.stats.IClusterRepoStatisticsReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterRepoStatistics;

import java.util.List;

/**
 * Controller for accessing the statistics
 *
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "stats", description = "retrieve statistics about the repository", position = 0)
@Controller
@RequestMapping(value = "/stats")
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @Autowired
    IClusterRepoStatisticsReadDao clusterRepoStatisticsReadDao;

    @ApiOperation(value = "returns the general statistics for the entire repository", position = 1, notes = "retrieve general statistics")
    @RequestMapping(value = "/general", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    RepoStatisticList getClusterRepoStatistics() {
        List<ClusterRepoStatistics> generalStatistics = clusterRepoStatisticsReadDao.getGeneralStatistics();
        return RepoStatsToWsStatsMapper.asStatsList(generalStatistics);
    }

    @ApiOperation(value = "returns the number of clusters per species", position = 2, notes = "retrieve the number of clusters per species")
    @RequestMapping(value = "/species/cluster/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    RepoStatisticList getClusterPerSpeciesStatistics() {
        List<ClusterRepoStatistics> statistics = clusterRepoStatisticsReadDao.getStatisticsByPrefix(ClusterRepoStatistics.NUMBER_OF_CLUSTERS_PER_SPECIES);
        for (ClusterRepoStatistics stat : statistics) {
            String[] names = stat.getName().split("-");
            stat.setName(names[1].trim());
        }
        return RepoStatsToWsStatsMapper.asStatsList(statistics);
    }

    @ApiOperation(value = "returns the number of unique peptides per species", position = 2, notes = "retrieve the number of unique peptides per species")
    @RequestMapping(value = "/species/peptide/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    RepoStatisticList getUniquePeptidesPerSpeciesStatistics() {
        List<ClusterRepoStatistics> statistics = clusterRepoStatisticsReadDao.getStatisticsByPrefix(ClusterRepoStatistics.NUMBER_OF_UNIQUE_PEPTIDES_PER_SPECIES);
        for (ClusterRepoStatistics stat : statistics) {
            String[] names = stat.getName().split("-");
            stat.setName(names[1].trim());
        }
        return RepoStatsToWsStatsMapper.asStatsList(statistics);
    }

    @ApiOperation(value = "returns the number of overlapping peptides for each pair of species", position = 3, notes = "retrieve the number of overlapping peptides for each pair of species")
    @RequestMapping(value = "/species/peptide/overlap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    RepoStatisticList getOverlappingPeptidesPerSpeciesPairStatistics() {
        List<ClusterRepoStatistics> statistics = clusterRepoStatisticsReadDao.getStatisticsByPrefix(ClusterRepoStatistics.OVERLAPPING_UNIQUE_PEPTIDES_ON_SPEICES);

        for (ClusterRepoStatistics stat : statistics) {
            String[] names = stat.getName().split("-");
            stat.setName(names[1].trim());
        }

        return RepoStatsToWsStatsMapper.asStatsList(statistics);
    }


}
