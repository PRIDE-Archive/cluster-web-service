package uk.ac.ebi.pride.cluster.ws.modules.project.controller;

/**
 * Web service end point for project
 *
 * @author Rui Wang
 * @version $Id$
 */
//@Api(value = "project", description = "retrieve summarised information about projects", position = 0)
//@Controller
//@RequestMapping(value = "/project")
public class ProjectController {

//    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

//    @Value("#{archiveProperties['archive.project.detail.url']}")
//    private String projectWsUrl;
//
//    @Autowired
//    IClusterReadDao clusterReaderDao;

//    @ApiOperation(value = "returns projects for a given Cluster ID", position = 1, notes = "retrieve projects for a given Cluster ID")
//    @RequestMapping(value = "/list/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK) // 200
//    public
//    @ResponseBody
//    ProjectList getClusterProjects(
//            @ApiParam(value = "a cluster ID")
//            @PathVariable("clusterId") long clusterId) {
//        logger.info("Cluster " + clusterId + " projects requested");
//
//        ProjectWsRetriever projectWsRetriever = new ProjectWsRetriever(projectWsUrl);
//
//        Set<String> projectAccessions = getProjectAccessions(clusterId);
//
//        return projectWsRetriever.getProjects(projectAccessions);
//    }

//    /**
//     * Get a set of unique project accession for a given cluster
//     *
//     * @param clusterId cluster id
//     * @return a set of project accessions
//     */
//    private Set<String> getProjectAccessions(long clusterId) {
//        // get cluster
//        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);
//
//        // get a unique list of projects
//        List<AssayDetail> assaySummaries = cluster.getAssayDetails();
//        Set<String> projectAccessions = new HashSet<String>();
//        for (AssayDetail assaySummary : assaySummaries) {
//            projectAccessions.add(assaySummary.getProjectAccession());
//        }
//
//        return projectAccessions;
//    }
}
