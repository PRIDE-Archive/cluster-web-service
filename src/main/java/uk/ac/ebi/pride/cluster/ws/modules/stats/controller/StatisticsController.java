package uk.ac.ebi.pride.cluster.ws.modules.stats.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.PSMDeltaMZStatistics;
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.RepoStatistic;
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.SpectrumSimilarityStatistics;
import uk.ac.ebi.pride.cluster.ws.modules.stats.util.ClusterStatsCollector;
import uk.ac.ebi.pride.cluster.ws.modules.stats.util.RepoStatsToWsStatsMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.cluster.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.dao.stats.IClusterRepoStatisticsReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
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
    public @ResponseBody List<RepoStatistic> getClusterRepoStatistics() {
        List<ClusterRepoStatistics> generalStatistics = clusterRepoStatisticsReadDao.getGeneralStatistics();
        return RepoStatsToWsStatsMapper.asStatsList(generalStatistics);
    }

    @ApiOperation(value = "returns delta m/z statistics for a given Cluster ID", position = 2, notes = "retrieve delta m/z statistics for a given Cluster ID")
    @RequestMapping(value = "/deltamz/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    PSMDeltaMZStatistics getClusterPsmDeltaMZStatistics(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " delta m/z statistics requested");

        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);
        return ClusterStatsCollector.collectPSMDeltaMZStatistics(cluster);
    }

    @ApiOperation(value = "returns spectrum similarity statistics for a given Cluster ID", position = 3, notes = "retrieve spectrum similarity statistics for a given Cluster ID")
    @RequestMapping(value = "/similarity/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    SpectrumSimilarityStatistics getClusterSpectrumSimilarityStatistics(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " spectrum similarity statistics requested");

        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);
        return ClusterStatsCollector.collectSpectrumSimilarityStatistics(cluster);
    }
}
