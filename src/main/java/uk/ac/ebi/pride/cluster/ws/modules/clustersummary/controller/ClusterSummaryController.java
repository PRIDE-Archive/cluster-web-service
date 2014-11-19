package uk.ac.ebi.pride.cluster.ws.modules.clustersummary.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.pride.cluster.search.model.Cluster;
import uk.ac.ebi.pride.cluster.search.model.SolrCluster;
import uk.ac.ebi.pride.cluster.search.service.IClusterSearchService;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.model.ClusterSummary;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.util.RepoClusterToWsClusterSummaryMapper;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.util.RepoClusterToWsClusterSummaryMapper;
import uk.ac.ebi.pride.cluster.ws.modules.clustersummary.util.SolrClusterToWsClusterSummaryMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.IClusterReadDao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
@Api(value = "clusterSummary", description = "retrieve summarised information about clusters", position = 0)
@Controller
@RequestMapping(value = "/clusterSummary")
public class ClusterSummaryController {

    private static final Logger logger = LoggerFactory.getLogger(ClusterSummaryController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @Autowired
    IClusterSearchService clusterSearchService;

    @ApiOperation(value = "retrieves cluster summary information by cluster ID", position = 1, notes = "retrieve a record of a specific clusterSummary")
    @RequestMapping(value = "/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    ClusterSummary getClusterSummary(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " summary requested");

        return RepoClusterToWsClusterSummaryMapper.asClusterSummary(clusterReaderDao.findCluster(clusterId));
    }

    @ApiOperation(value = "list clusters summaries for given criteria", position = 2, notes = "search functionality")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<ClusterSummary> simpleSearchClusters(
            @ApiParam(value = "general search term against multiple fields including: Max Ratio Peptide Sequence")
            @RequestParam(value = "q", required = false, defaultValue = "") String q,
            @ApiParam(value = "specific search term against Max Ratio Peptide Sequence")
            @RequestParam(value = "peptide", required = false, defaultValue = "") String peptide,
            @ApiParam(value = "specific search term against Species in a Cluster")
            @RequestParam(value = "species", required = false, defaultValue = "") String species,
            @ApiParam(value = "specific search term against Protein Accessions in a Cluster")
            @RequestParam(value = "protein", required = false, defaultValue = "") String protein,
            @ApiParam(value = "specific search term against Project Accessions in a Cluster")
            @RequestParam(value = "project", required = false, defaultValue = "") String project,
            @ApiParam(value = "1-based page number")
            @RequestParam(value = "page", required = true) int page,
            @ApiParam(value = "maximum number of results per page")
            @RequestParam(value = "size", required = true) int size
    ) {

        logger.info("Fetched cluster summaries for\n" +
                " query: " + q + "\n" +
                " peptide: " + peptide + "\n" +
                " species: " + species + "\n" +
                " protein: " + protein + "\n" +
                " project: " + project + "\n" +
                " page: " + page + "\n" +
                " size: " + size
        );

        int solrPage = page - 1; // Spring data for Solr uses 0-based paging
        Page<SolrCluster> res;
        if ("".equals(q)) {
            res = clusterSearchService.findAll(new PageRequest(solrPage,size));
        } else {
            Set<String> seqs = new HashSet<String>();
            for (String seq : q.split(" ")) {
                seqs.add(seq);
            }
            res = clusterSearchService.findByHighestRatioPepSequences(seqs, new PageRequest(solrPage, size));
        }

        return SolrClusterToWsClusterSummaryMapper.asClusterSummaryList(res);
    }

    @ApiOperation(value = "list similar cluster summaries given a list of peaks", position = 3, notes = "additive clustering functionality")
    @RequestMapping(value = "/nearest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<ClusterSummary> getSimilarClusters(
            @ApiParam(value = "peak list to compare to")
            @RequestParam(value = "peaks", required = false, defaultValue = "") String peaks
    ) {

        logger.debug("Fetched clusters for peak list: " + peaks);

        return getTestClusterSummaries(5);
    }


    private List<ClusterSummary> getTestClusterSummaries(int n) {
        List<ClusterSummary> res = new LinkedList<ClusterSummary>();

        for (int i=0;i<n;i++) {
            ClusterSummary clusterSummary = new ClusterSummary();
            clusterSummary.setId(i);
            res.add(clusterSummary);
        }

        return res;
    }
}
