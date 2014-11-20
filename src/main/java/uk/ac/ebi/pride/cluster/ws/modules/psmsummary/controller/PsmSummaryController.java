package uk.ac.ebi.pride.cluster.ws.modules.psmsummary.controller;

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
import uk.ac.ebi.pride.cluster.ws.modules.psmsummary.model.PsmSummary;
import uk.ac.ebi.pride.cluster.ws.modules.psmsummary.util.RepoPsmToWsPsmSummaryMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.IClusterReadDao;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
@Api(value = "psmSummary", description = "retrieve summarised information about PSMs", position = 0)
@Controller
@RequestMapping(value = "/psmSummary")
public class PsmSummaryController {

    private static final Logger logger = LoggerFactory.getLogger(PsmSummaryController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @ApiOperation(value = "returns PSMs for a given Cluster ID", position = 1, notes = "retrieve PSMs for a given Cluster ID")
    @RequestMapping(value = "/{clusterId}/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<PsmSummary> getClusterPsms(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " PSMs requested");

        return RepoPsmToWsPsmSummaryMapper.asPsmSummaryList(clusterReaderDao.findClusteredPSMSummaryByClusterId(clusterId));

    }
}
