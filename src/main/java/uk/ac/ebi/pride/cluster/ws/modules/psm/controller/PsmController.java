package uk.ac.ebi.pride.cluster.ws.modules.psm.controller;

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
import uk.ac.ebi.pride.cluster.ws.modules.psm.model.Psm;
import uk.ac.ebi.pride.cluster.ws.modules.psm.util.RepoPsmToWsPsmMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
@Api(value = "psm", description = "retrieve summarised information about PSMs", position = 0)
@Controller
@RequestMapping(value = "/psm")
public class PsmController {

    private static final Logger logger = LoggerFactory.getLogger(PsmController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @ApiOperation(value = "returns PSMs for a given Cluster ID", position = 1, notes = "retrieve PSMs for a given Cluster ID")
    @RequestMapping(value = "/list/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<Psm> getClusterPsms(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " PSMs requested");

        List<ClusteredPSMDetail> clusteredPSMDetails = clusterReaderDao.findClusteredPSMSummaryByClusterId(clusterId);
        return RepoPsmToWsPsmMapper.asPsmList(clusteredPSMDetails);
    }
}
