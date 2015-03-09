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
import uk.ac.ebi.pride.cluster.ws.modules.psm.model.PsmList;
import uk.ac.ebi.pride.cluster.ws.modules.psm.model.QualityAwarePsm;
import uk.ac.ebi.pride.cluster.ws.modules.psm.model.QualityAwarePsmList;
import uk.ac.ebi.pride.cluster.ws.modules.psm.util.RepoPsmToWsPsmMapper;
import uk.ac.ebi.pride.spectracluster.repo.dao.cluster.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary;
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
    PsmList getClusterPsms(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " PSMs requested");

        List<ClusteredPSMDetail> clusteredPSMDetails = clusterReaderDao.findClusteredPSMSummaryByClusterId(clusterId);
        return RepoPsmToWsPsmMapper.asPsmList(clusteredPSMDetails);
    }


    @ApiOperation(value = "returns quality aware PSMs for a given PRIDE Archive PSM ID", position = 1, notes = "retrieve PSMs with cluster quality annotations")
    @RequestMapping(value = "/archive/{archivePsmId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    QualityAwarePsmList getQualityAwarePsms(
            @ApiParam(value = "a PRIDE Archive PSM ID")
            @PathVariable("archivePsmId") String archivePsmId) {
        logger.info("PRIDE Archive PSM " + archivePsmId + " requested");

        QualityAwarePsmList qualityAwarePsmList = new QualityAwarePsmList();

        List<ClusteredPSMDetail> clusteredPSMSummaries = clusterReaderDao.findClusteredPSMSummaryByArchiveId(archivePsmId);

        if (!clusteredPSMSummaries.isEmpty()) {
            for (ClusteredPSMDetail clusteredPSMSummary : clusteredPSMSummaries) {
                // get cluster summary first
                Long clusterId = clusteredPSMSummary.getClusterId();
                ClusterSummary clusterSummary = clusterReaderDao.findClusterSummary(clusterId);

                // construct quality aware psm
                QualityAwarePsm qualityAwarePsm = RepoPsmToWsPsmMapper.asQualityAwarePsm(clusteredPSMSummary, clusterSummary);
                qualityAwarePsmList.addQualityAwarePsm(qualityAwarePsm);
            }
        }

        return qualityAwarePsmList;
    }


}
