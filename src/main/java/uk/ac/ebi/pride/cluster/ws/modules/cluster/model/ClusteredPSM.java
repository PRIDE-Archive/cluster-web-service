package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;

import java.util.List;

/**
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredPSM extends Peptide {

    private Long clusterId; // internal cluster id
    private Long psmId; // internal psm id
    private Long spectrumId; // internal spectrum id
    private float psmRatio; // ratio is the number of distinct psm / the number of spectra
    private float rank;
    private Long assayId;
    private String archivePSMId;
    private List<ModificationProvider> standardisedModifications;
    private String searchEngine;
    private String searchEngineScores;
    private String searchDatabase;
    private String proteinAccession;
    private String proteinGroup;
    private String proteinName;
    private int startPosition = -1;
    private int stopPosition = -1;
    private String preAminoAcid;
    private String postAminoAcid;
    private float deltaMZ;
    private String quantificationLabel;

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    public Long getPsmId() {
        return psmId;
    }

    public void setPsmId(Long psmId) {
        this.psmId = psmId;
    }

    public Long getSpectrumId() {
        return spectrumId;
    }

    public void setSpectrumId(Long spectrumId) {
        this.spectrumId = spectrumId;
    }

    public float getPsmRatio() {
        return psmRatio;
    }

    public void setPsmRatio(float psmRatio) {
        this.psmRatio = psmRatio;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public Long getAssayId() {
        return assayId;
    }

    public void setAssayId(Long assayId) {
        this.assayId = assayId;
    }

    public String getArchivePSMId() {
        return archivePSMId;
    }

    public void setArchivePSMId(String archivePSMId) {
        this.archivePSMId = archivePSMId;
    }

    public List<ModificationProvider> getStandardisedModifications() {
        return standardisedModifications;
    }

    public void setStandardisedModifications(List<ModificationProvider> standardisedModifications) {
        this.standardisedModifications = standardisedModifications;
    }

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public String getSearchEngineScores() {
        return searchEngineScores;
    }

    public void setSearchEngineScores(String searchEngineScores) {
        this.searchEngineScores = searchEngineScores;
    }

    public String getSearchDatabase() {
        return searchDatabase;
    }

    public void setSearchDatabase(String searchDatabase) {
        this.searchDatabase = searchDatabase;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public String getProteinGroup() {
        return proteinGroup;
    }

    public void setProteinGroup(String proteinGroup) {
        this.proteinGroup = proteinGroup;
    }

    public String getProteinName() {
        return proteinName;
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getStopPosition() {
        return stopPosition;
    }

    public void setStopPosition(int stopPosition) {
        this.stopPosition = stopPosition;
    }

    public String getPreAminoAcid() {
        return preAminoAcid;
    }

    public void setPreAminoAcid(String preAminoAcid) {
        this.preAminoAcid = preAminoAcid;
    }

    public String getPostAminoAcid() {
        return postAminoAcid;
    }

    public void setPostAminoAcid(String postAminoAcid) {
        this.postAminoAcid = postAminoAcid;
    }

    public float getDeltaMZ() {
        return deltaMZ;
    }

    public void setDeltaMZ(float deltaMZ) {
        this.deltaMZ = deltaMZ;
    }

    public String getQuantificationLabel() {
        return quantificationLabel;
    }

    public void setQuantificationLabel(String quantificationLabel) {
        this.quantificationLabel = quantificationLabel;
    }
}
