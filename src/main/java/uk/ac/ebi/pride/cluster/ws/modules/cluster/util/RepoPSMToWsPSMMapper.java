package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.ClusteredPSM;
import uk.ac.ebi.pride.spectracluster.repo.model.AssayDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.PSMDetail;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class RepoPSMToWsPSMMapper {

    public static List<ClusteredPSM> asPSMList(List<ClusteredPSMDetail> repoPSMs, ClusterDetail cluster) {
        ArrayList<ClusteredPSM> clusteredPSMs = new ArrayList<ClusteredPSM>();

        for (ClusteredPSMDetail repoPSM : repoPSMs) {
            clusteredPSMs.add(asPSM(repoPSM, cluster));
        }

        return clusteredPSMs;
    }


    public static ClusteredPSM asPSM(ClusteredPSMDetail repoPSM, ClusterDetail cluster) {
        ClusteredPSM clusteredPSM = new ClusteredPSM();

        clusteredPSM.setClusterId(repoPSM.getClusterId());
        clusteredPSM.setPsmId(repoPSM.getPsmId());
        clusteredPSM.setPsmRatio(repoPSM.getPsmRatio());
        clusteredPSM.setRank(repoPSM.getRank());
        clusteredPSM.setSequence(repoPSM.getSequence());
        clusteredPSM.setSpectrumId(repoPSM.getSpectrumId());

        PSMDetail repoPSMDetail = repoPSM.getPsmDetail();
        clusteredPSM.setArchivePSMId(repoPSMDetail.getArchivePSMId());
        clusteredPSM.setModifications(new ArrayList<ModificationProvider>(repoPSMDetail.getModifications()));
        clusteredPSM.setStandardisedModifications(new ArrayList<ModificationProvider>(repoPSMDetail.getStandardisedModifications()));
        clusteredPSM.setSearchEngine(repoPSMDetail.getSearchEngine());
        clusteredPSM.setSearchEngineScores(repoPSMDetail.getSearchEngineScores());
        clusteredPSM.setSearchDatabase(repoPSMDetail.getSearchDatabase());
        clusteredPSM.setProteinAccession(repoPSMDetail.getProteinAccession());
        clusteredPSM.setProteinGroup(repoPSMDetail.getProteinGroup());
        clusteredPSM.setProteinName(repoPSMDetail.getProteinName());
        clusteredPSM.setStartPosition(repoPSMDetail.getStartPosition());
        clusteredPSM.setStopPosition(repoPSMDetail.getStopPosition());
        clusteredPSM.setPreAminoAcid(repoPSMDetail.getPreAminoAcid());
        clusteredPSM.setPostAminoAcid(repoPSMDetail.getPostAminoAcid());
        clusteredPSM.setDeltaMZ(repoPSMDetail.getDeltaMZ());
        clusteredPSM.setQuantificationLabel(repoPSMDetail.getQuantificationLabel());

        Long assayId = repoPSMDetail.getAssayId();
        AssayDetail assayDetail = cluster.getAssayDetail(assayId);
        clusteredPSM.setProjectAccession(assayDetail.getProjectAccession());
        clusteredPSM.setAssayAccession(assayDetail.getAccession());
        clusteredPSM.setSpecies(assayDetail.getSpecies());
        clusteredPSM.setTissues(assayDetail.getTissue());
        clusteredPSM.setDiseases(assayDetail.getDisease());
        clusteredPSM.setInstruments(assayDetail.getInstrument());

        return clusteredPSM;
    }
}
