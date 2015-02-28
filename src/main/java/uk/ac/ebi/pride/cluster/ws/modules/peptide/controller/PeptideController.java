package uk.ac.ebi.pride.cluster.ws.modules.peptide.controller;

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
import uk.ac.ebi.pride.cluster.ws.modules.peptide.model.ClusteredPeptide;
import uk.ac.ebi.pride.cluster.ws.modules.peptide.util.ClusteredPeptideFinder;
import uk.ac.ebi.pride.spectracluster.repo.dao.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;

import java.util.List;

/**
 *
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "peptide", description = "retrieve summarised information about peptides", position = 0)
@Controller
@RequestMapping(value = "/peptide")
public class PeptideController {

    private static final Logger logger = LoggerFactory.getLogger(PeptideController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @ApiOperation(value = "returns peptides for a given Cluster ID", position = 1, notes = "retrieve peptides for a given Cluster ID")
    @RequestMapping(value = "/list/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<ClusteredPeptide> getClusterPeptides(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " peptides requested");

        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);
        return ClusteredPeptideFinder.findClusteredPeptides(cluster);
    }
}
