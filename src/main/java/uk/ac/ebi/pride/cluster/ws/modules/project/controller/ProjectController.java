package uk.ac.ebi.pride.cluster.ws.modules.project.controller;

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
import uk.ac.ebi.pride.cluster.ws.modules.project.model.Project;
import uk.ac.ebi.pride.cluster.ws.modules.project.util.ProjectWsRetriever;
import uk.ac.ebi.pride.spectracluster.repo.dao.cluster.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Web service end point for project
 *
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "project", description = "retrieve summarised information about projects", position = 0)
@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    IClusterReadDao clusterReaderDao;

    @Autowired
    ProjectWsRetriever projectWsRetriever;

    @ApiOperation(value = "returns projects for a given Cluster ID", position = 1, notes = "retrieve projects for a given Cluster ID")
    @RequestMapping(value = "/list/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    List<Project> getClusterProjects(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " projects requested");

        Set<String> projectAccessions = getProjectAccessions(clusterId);

        return projectWsRetriever.getProjects(projectAccessions);
    }

    /**
     * Get a set of unique project accession for a given cluster
     *
     * @param clusterId cluster id
     * @return a set of project accessions
     */
    private Set<String> getProjectAccessions(long clusterId) {
        // get cluster
        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);

        // get a unique list of projects
        List<AssayDetail> assaySummaries = cluster.getAssayDetails();
        Set<String> projectAccessions = new HashSet<String>();
        for (AssayDetail assaySummary : assaySummaries) {
            projectAccessions.add(assaySummary.getProjectAccession());
        }

        return projectAccessions;
    }
}
