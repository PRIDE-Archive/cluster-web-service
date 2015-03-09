package uk.ac.ebi.pride.cluster.ws.modules.cluster.controller;

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
import uk.ac.ebi.pride.cluster.search.model.SolrCluster;
import uk.ac.ebi.pride.cluster.search.service.IClusterSearchService;
import uk.ac.ebi.pride.cluster.ws.error.exception.ResourceNotFoundException;
import uk.ac.ebi.pride.cluster.ws.modules.assay.model.PTMCount;
import uk.ac.ebi.pride.cluster.ws.modules.assay.model.PTMDistribution;
import uk.ac.ebi.pride.cluster.ws.modules.assay.model.SpeciesCount;
import uk.ac.ebi.pride.cluster.ws.modules.assay.model.SpeciesDistribution;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.*;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.util.ClusterStatsCollector;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.util.ClusteredPeptideFinder;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.util.RepoClusterToWsClusterMapper;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.util.SolrClusterToWsClusterMapper;
import uk.ac.ebi.pride.cluster.ws.modules.spectrum.model.Spectrum;
import uk.ac.ebi.pride.spectracluster.repo.dao.cluster.IClusterReadDao;
import uk.ac.ebi.pride.spectracluster.repo.model.*;

import java.util.*;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 */
@Api(value = "cluster", description = "retrieve information about clusters", position = 0)
@Controller
@RequestMapping(value = "/cluster")
public class ClusterController {

    private static final Logger logger = LoggerFactory.getLogger(ClusterController.class);

    public static final float DEFAULT_MINIMUM_PSM_RANKING = 1.1f;

    @Autowired
    IClusterReadDao clusterReaderDao;

    @Autowired
    IClusterSearchService clusterSearchService;

    @ApiOperation(value = "retrieves cluster information by cluster ID", position = 1, notes = "retrieve a record of a specific cluster")
    @RequestMapping(value = "/{clusterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    Cluster getClusterSummary(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster summary " + clusterId + " requested");

        ClusterSummary clusterSummary = clusterReaderDao.findClusterSummary(clusterId);
        List<ClusteredPSMDetail> clusteredPSMSummaries = clusterReaderDao.findClusteredPSMSummaryByClusterId(clusterId, DEFAULT_MINIMUM_PSM_RANKING);
        return RepoClusterToWsClusterMapper.asCluster(clusterSummary, clusteredPSMSummaries.get(0));
    }

    @ApiOperation(value = "list clusters for given search criteria", position = 2, notes = "search functionality")
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    ClusterSearchResults simpleSearchClusters(
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
            @ApiParam(value = "0-based page number")
            @RequestParam(value = "page", required = true, defaultValue = "0") int page,
            @ApiParam(value = "maximum number of results per page")
            @RequestParam(value = "size", required = true, defaultValue = "10") int size
    ) {

        logger.info("Fetched clusters for\n" +
                        " query: " + q + "\n" +
                        " peptide: " + peptide + "\n" +
                        " species: " + species + "\n" +
                        " protein: " + protein + "\n" +
                        " project: " + project + "\n" +
                        " page: " + page + "\n" +
                        " size: " + size
        );

        Page<SolrCluster> res;

        if ("".equals(q)) {
            res = clusterSearchService.findAll(new PageRequest(page, size));
        } else {
            Set<String> seqs = new HashSet<String>();
            Collections.addAll(seqs, q.split(" "));
            res = clusterSearchService.findByHighestRatioPepSequences(seqs, new PageRequest(page, size));
        }

        ClusterSearchResults results = new ClusterSearchResults();
        results.setPageNumber(page);
        results.setPageSize(size);
        results.setTotalResults(res.getTotalElements());
        logger.info("Total results is " + results.getTotalResults());
        results.setResults(SolrClusterToWsClusterMapper.asClusterList(res));

        return results;
    }


    @ApiOperation(value = "a convenience endpoint that retrieves cluster species information only", position = 3, notes = "retrieve a record of a specific cluster consensus spectrum")
    @RequestMapping(value = "/{clusterId}/species", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    ClusterSpeciesCounts getClusterSpecies(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " species requested");

        // get the cluster
        uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail repoCluster = clusterReaderDao.findCluster(clusterId);
        // Get the assays for a given cluster
        List<AssayDetail> repoAssays = repoCluster.getAssayDetails();
        // Extract the species
        SpeciesDistribution species = new SpeciesDistribution();
        for (AssayDetail repoAssay : repoAssays) {
            for (String aSpecies : repoAssay.getSpeciesEntries()) {
                if (species.getDistribution().containsKey(aSpecies)) {
                    species.getDistribution().get(aSpecies).addSpeciesCount(1);
                } else {
                    SpeciesCount newSpeciesCount = new SpeciesCount(aSpecies, 1);
                    species.getDistribution().put(aSpecies, newSpeciesCount);
                }
            }

        }

        ClusterSpeciesCounts res = new ClusterSpeciesCounts();
        res.setSpeciesCounts(new ArrayList<SpeciesCount>(species.getDistribution().values()));
        return res;

    }

    @ApiOperation(value = "a convenience endpoint that retrieves cluster PTM information only", position = 4, notes = "retrieve PTM records of a specific cluster")
    @RequestMapping(value = "/{clusterId}/ptms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    ClusterPTMCounts getClusterPTMs(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " PTMs requested");

        // get the cluster
        uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail repoCluster = clusterReaderDao.findCluster(clusterId);

        // Get the clustered psm details for a given cluster
        List<ClusteredPSMDetail> clusteredPSMDetails = repoCluster.getClusteredPSMDetails();

        // Extract the PTMs
        PTMDistribution ptms = new PTMDistribution();
        for (ClusteredPSMDetail clusteredPSMDetail : clusteredPSMDetails) {
            //todo: change to use standardised modifications
            List<PTMDetail> modifications = clusteredPSMDetail.getPsmDetail().getModifications();

            if (modifications.isEmpty()) {
                if (ptms.getDistribution().containsKey(PTMDistribution.NO_MODIFICATIONS)) {
                    ptms.getDistribution().get(PTMDistribution.NO_MODIFICATIONS).addPTMCount(1);
                } else {
                    PTMCount ptmCount = new PTMCount(PTMDistribution.NO_MODIFICATIONS, null, 1);
                    ptms.getDistribution().put(PTMDistribution.NO_MODIFICATIONS, ptmCount);
                }
            } else {
                for (PTMDetail modification : modifications) {
                    if (ptms.getDistribution().containsKey(modification.getAccession())) {
                        ptms.getDistribution().get(modification.getAccession()).addPTMCount(1);
                    } else {
                        PTMCount ptmCount = new PTMCount(modification.getName(), modification.getAccession(), 1);
                        ptms.getDistribution().put(modification.getAccession(), ptmCount);
                    }
                }
            }
        }

        ClusterPTMCounts res = new ClusterPTMCounts();
        res.setPtmCounts(new ArrayList<PTMCount>(ptms.getDistribution().values()));
        return res;

    }


    @ApiOperation(value = "a convenience endpoint that retrieves cluster consensus spectrum information only", position = 5, notes = "retrieve a record of a specific cluster consensus spectrum")
    @RequestMapping(value = "/{clusterId}/consensus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    Spectrum getClusterConsensusSpectrum(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " consensus spectra requested");

        ClusterSummary repoCluster = clusterReaderDao.findCluster(clusterId);
        if (repoCluster != null) {
            return RepoClusterToWsClusterMapper.asConsensusSpectrum(clusterReaderDao.findCluster(clusterId));
        } else {
            throw new ResourceNotFoundException("Cluster with ID " + clusterId + " not found in DB");
        }

    }

    @ApiOperation(value = "returns peptides for a given Cluster ID", position = 6, notes = "retrieve peptides for a given Cluster ID")
    @RequestMapping(value = "/{clusterId}/peptide", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    ClusteredPeptideList getClusterPeptides(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " peptides requested");

        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);
        return ClusteredPeptideFinder.findClusteredPeptides(cluster);
    }

    @ApiOperation(value = "returns delta m/z statistics for a given Cluster ID", position = 7, notes = "retrieve delta m/z statistics for a given Cluster ID")
    @RequestMapping(value = "/{clusterId}/deltamz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    PSMDeltaMZStatistics getClusterPsmDeltaMZStatistics(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " delta m/z statistics requested");

        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);
        return ClusterStatsCollector.collectPSMDeltaMZStatistics(cluster);
    }

    @ApiOperation(value = "returns spectrum similarity statistics for a given Cluster ID", position = 8, notes = "retrieve spectrum similarity statistics for a given Cluster ID")
    @RequestMapping(value = "/{clusterId}/similarity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    SpectrumSimilarityStatistics getClusterSpectrumSimilarityStatistics(
            @ApiParam(value = "a cluster ID")
            @PathVariable("clusterId") long clusterId) {
        logger.info("Cluster " + clusterId + " spectrum similarity statistics requested");

        ClusterDetail cluster = clusterReaderDao.findCluster(clusterId);
        return ClusterStatsCollector.collectSpectrumSimilarityStatistics(cluster);
    }

//    @ApiOperation(value = "list similar cluster summaries given a list of peaks", position = 6, notes = "additive clustering functionality")
//    @RequestMapping(value = "/nearest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK) // 200
//    public
//    @ResponseBody
//    ClusterSearchResults getSimilarClusters(
//            @ApiParam(value = "precursor MZ")
//            @RequestParam(value = "precursor", required = false, defaultValue = "") String precursor,
//            @ApiParam(value = "peak list to compare to")
//            @RequestParam(value = "peaks", required = false, defaultValue = "") String peaks,
//            @ApiParam(value = "0-based page number")
//            @RequestParam(value = "page", required = true, defaultValue = "0") int page,
//            @ApiParam(value = "maximum number of results per page")
//            @RequestParam(value = "size", required = true, defaultValue = "10") int size
//
//    ) {
//
//        logger.info("Fetched clusters for ");
//        logger.info("page: " + page);
//        logger.info("size: " + size);
//        logger.info("precursor: " + precursor);
//        logger.info("peaks ");
//        logger.info(peaks);
//
//        QueryInputPeaks query = new QueryInputPeaks();
//        parsePeaks(peaks, query);
//        double precursorMz = Double.parseDouble(precursor);
//
//        double[] lowResMz = LowResUtils.toLowResByBucketMean(query.mzValues, 20);
//        double[] lowResIntensity = LowResUtils.toLowResByBucketMean(query.intensityValues, 20);
//        logDoubleArray("MZ", lowResMz);
//        logDoubleArray("Intensity", lowResIntensity);
//
//        Page<SolrCluster> clusters = clusterSearchService.findByNearestPeaks(
//                "HIGH",
//                precursorMz,
//                1.0,
//                lowResMz,
//                lowResIntensity,
//                new PageRequest(page, size)
//        );
//
//        ClusterSearchResults results = new ClusterSearchResults();
//        results.setPageNumber(page);
//        results.setPageSize(size);
//        results.setTotalResults(clusters.getTotalElements());
//        logger.info("Total results is " + clusters.getTotalElements());
//        results.setResults(SolrClusterToWsClusterMapper.asClusterList(clusters));
//
//        return results;
//
//    }
//
//    private void logDoubleArray(String tag, double[] values) {
//        for (double va : values) {
//            logger.info(tag + " is " + va);
//        }
//    }
//
//    private void parsePeaks(String peaks, QueryInputPeaks query) {
//        String[] peakStrings = peaks.split("[ \\n]+");
//        query.mzValues = new double[peakStrings.length / 2];
//        query.intensityValues = new double[peakStrings.length / 2];
//
//
//        int j = 0;
//        for (int i = 0; i < peakStrings.length; i = i + 2) {
//            query.mzValues[j] = Double.parseDouble(peakStrings[i]);
//            query.intensityValues[j] = Double.parseDouble(peakStrings[i + 1]);
//            j++;
//        }
//    }
//
//    private List<Cluster> getTestClusters(int n) {
//        List<Cluster> res = new LinkedList<Cluster>();
//
//        for (int i = 0; i < n; i++) {
//            Cluster cluster = new Cluster();
//            cluster.setId(i);
//            res.add(cluster);
//        }
//
//        return res;
//    }
}
