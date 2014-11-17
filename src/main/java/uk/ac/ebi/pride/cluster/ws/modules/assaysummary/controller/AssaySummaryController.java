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
import uk.ac.ebi.pride.cluster.ws.error.exception.ResourceNotFoundException;
import uk.ac.ebi.pride.cluster.ws.modules.assaysummary.model.AssaySummary;
import uk.ac.ebi.pride.cluster.ws.modules.assaysummary.model.Species;
import uk.ac.ebi.pride.cluster.ws.modules.assaysummary.util.RepoAssaySummaryToWsAssaySummaryMapper;
import uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.model.ClusterDetail;
import uk.ac.ebi.pride.cluster.ws.modules.clusterdetail.util.RepoClusterToWsClusterDetailMapper;
import uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.model.Spectrum;
import uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.model.SpectrumPeak;
import uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.model.SpectrumSummary;
import uk.ac.ebi.pride.cluster.ws.modules.spectrumsummary.util.RepoSpectrumToWsSpectrumSummaryMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary;

import java.util.LinkedList;
import java.util.List;

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

    @ApiOperation(value = "retrieves assay summary information for a particular cluster", position = 1, notes = "retrieve a list of assay summaries")
    @RequestMapping(value = "/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<AssaySummary> getClusterAssaySummary(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Assay summaries for Cluster " + clusterId + " requested");

        return RepoAssaySummaryToWsAssaySummaryMapper.asAssaySummaryList(/*clusterReaderDao.getSpecies(clusterId)*/);
    }

    @ApiOperation(value = "a convenience endpoint that retrieves cluster consensus spectrum information only", position = 1, notes = "retrieve a record of a specific cluster consensus spectrum")
    @RequestMapping(value = "/{clusterId}/species", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<Species> getClusterSpecies(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " consensus spectra requested");

        return null;

    }
}
