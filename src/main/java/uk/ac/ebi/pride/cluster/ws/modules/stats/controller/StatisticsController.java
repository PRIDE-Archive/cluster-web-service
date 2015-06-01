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
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.RepoChordChartStatistic;
import uk.ac.ebi.pride.cluster.ws.modules.stats.model.RepoStatisticList;
import uk.ac.ebi.pride.cluster.ws.modules.stats.util.RepoStatsToWsStatsMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.cluster.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.dao.stats.IClusterRepoStatisticsReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterRepoStatistics;

import java.util.*;

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
    @RequestMapping(value = "/species/peptide/overlap/{numbOfSpecies}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    RepoChordChartStatistic getOverlappingPeptidesPerSpeciesPairStatistics(
            @ApiParam(value = "the number of species")
            @PathVariable("numbOfSpecies") int numOfSpecies) {
        List<ClusterRepoStatistics> statistics = clusterRepoStatisticsReadDao.getStatisticsByPrefix(ClusterRepoStatistics.OVERLAPPING_UNIQUE_PEPTIDES_ON_SPEICES);

        // pre-process the statistics into a map
        Map<String, Map<String, Long>> stats = new HashMap<String, Map<String, Long>>();
        Map<String, Long> counts = new HashMap<String, Long>();
        for (ClusterRepoStatistics stat : statistics) {
            String[] names = stat.getName().split("-");
            String speciesOneName = names[1];
            String speciesTwoName = names[2];
            Long count = stat.getValue();

            // store the stats
            Map<String, Long> values = stats.get(speciesOneName);
            if (values == null) {
                values = new HashMap<String, Long>();
                stats.put(speciesOneName, values);
            }
            values.put(speciesTwoName, count);

            // total counts
            Long cnt = counts.get(speciesOneName);
            if (speciesOneName.equals("Escherichia coli str. K")) {
                System.out.println("reach");
            }
            if (cnt == null) {
                counts.put(speciesOneName, count);
            } else {
                counts.put(speciesOneName, cnt + count);
            }
        }

        // sort the map according to the total number of counts
        ValueComparator valueComparator = new ValueComparator(counts);
        TreeMap<String, Map<String, Long>> sortedStats = new TreeMap<String, Map<String, Long>>(valueComparator);
        sortedStats.putAll(stats);

        long ecoliCount = counts.get("Escherichia coli str. K");
        Map<String, Long> testNumbers = stats.get("Escherichia coli str. K");

        // only keep the top species that has the most counts
        String[] topSpecies = new String[numOfSpecies];
        Long[][] topSpeciesCounts = new Long[numOfSpecies][numOfSpecies];

        int index = 0;
        for (String species : sortedStats.keySet()) {
            if (index >= numOfSpecies) {
                break;
            }

            topSpecies[index] = species;
            index++;
        }

        for (int i = 0; i < numOfSpecies; i++) {
            Map<String, Long> speciesCounts = stats.get(topSpecies[i]);
            for (int j = 0; j < numOfSpecies; j++) {
                String targetSpecies = topSpecies[j];
                if (!speciesCounts.containsKey(targetSpecies)) {
                    topSpeciesCounts[i][j] = 0l;
                } else {
                    topSpeciesCounts[i][j] = speciesCounts.get(targetSpecies);
                }
            }
        }

        // construct result
        RepoChordChartStatistic repoChordChartStatistic = new RepoChordChartStatistic();
        repoChordChartStatistic.setLabels(topSpecies);
        repoChordChartStatistic.setCounts(topSpeciesCounts);

        return repoChordChartStatistic;
    }


    /**
     * Comparator for comparing
     *
     * WARNING: there is a draw back with this comparator, it overwrites the default comparator for keys
     */
    private static class ValueComparator implements Comparator<String> {

        private Map<String, Long> base;

        public ValueComparator(Map<String, Long> base) {
            this.base = base;
        }

        @Override
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
