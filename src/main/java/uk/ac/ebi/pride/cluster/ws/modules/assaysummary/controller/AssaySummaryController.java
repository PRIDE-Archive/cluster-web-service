package uk.ac.ebi.pride.cluster.ws.modules.assaysummary.controller;

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
import uk.ac.ebi.pride.spectracluster.repo.dao.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
@Api(value = "assaySummary", description = "retrieve summarised information about assays", position = 0)
@Controller
@RequestMapping(value = "/assaySummary")
public class AssaySummaryController {

    private static final Logger logger = LoggerFactory.getLogger(AssaySummaryController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @ApiOperation(value = "a convenience endpoint that retrieves cluster consensus spectrum information only", position = 1, notes = "retrieve a record of a specific cluster consensus spectrum")
    @RequestMapping(value = "/{clusterId}/species", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    Set<String> getClusterSpecies(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " consensus spectra requested");

        // get the cluster
        ClusterSummary repoCluster = clusterReaderDao.findCluster(clusterId);
        // Get the assays for a given cluster
        List<uk.ac.ebi.pride.spectracluster.repo.model.AssaySummary> repoAssays = repoCluster.getAssaySummaries();
        // Extract the species
        Set<String> species = new HashSet<String>();
        for (uk.ac.ebi.pride.spectracluster.repo.model.AssaySummary repoAssay: repoAssays) {
            species.addAll(repoAssay.getSpeciesEntries());
        }

        return species;

    }
}
