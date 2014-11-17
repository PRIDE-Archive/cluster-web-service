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

}
