package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.cluster.ws.modules.cluster.filter.IPredicate;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredProject;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredProjectList;
import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find clustered projects for a given cluster
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class ClusteredProjectFinder {

    public static ClusteredProjectList findClusteredProjects(ClusterDetail cluster, IPredicate<ClusteredPSMDetail> psmFilter) {
        ClusteredProjectList clusteredProjectList = new ClusteredProjectList();

        Map<String, ClusteredProject> clusteredProjects = new HashMap<String, ClusteredProject>();

        List<ClusteredPSMDetail> clusteredPSMs = cluster.getClusteredPSMDetails();


        for (ClusteredPSMDetail clusteredPSM : clusteredPSMs) {

            // filter by psm
            if (!psmFilter.apply(clusteredPSM)) {
                continue;
            }

            Long assayId = clusteredPSM.getPsmDetail().getAssayId();
            AssayDetail assayDetail = cluster.getAssayDetail(assayId);

            ClusteredProject clusteredProject = clusteredProjects.get(assayDetail.getProjectAccession());
            if (clusteredProject == null) {
                clusteredProject = new ClusteredProject();

                clusteredProject.setAccession(assayDetail.getProjectAccession());
                clusteredProject.setTitle(assayDetail.getProjectTitle());
                clusteredProject.setSpecies(assayDetail.getSpeciesEntries());
                clusteredProject.setTissues(assayDetail.getTissueEntries());
                clusteredProject.setDiseases(assayDetail.getDiseaseEntries());
                clusteredProject.setInstruments(assayDetail.getInstrumentEntries());
                clusteredProject.setSearchEngines(assayDetail.getSearchEngineEntries());

                clusteredProjects.put(assayDetail.getProjectAccession(), clusteredProject);
            }

            clusteredProject.incrementNumberOfPSMs(1);
        }

        clusteredProjectList.addClusteredProjects(clusteredProjects.values());

        return clusteredProjectList;
    }
}
