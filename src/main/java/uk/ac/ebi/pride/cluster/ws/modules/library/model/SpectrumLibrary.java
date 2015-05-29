package uk.ac.ebi.pride.cluster.ws.modules.library.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Details of one spectrum library, including download links
 *
 * @author Rui Wang
 * @version $Id$
 */
public class SpectrumLibrary {
    private Long taxonomyId;
    private String speciesScientificName;
    private String speciesName;
    private long numberOfSpectra;
    private long numberOfPeptides;
    private long fileSize;
    private final List<DownloadURL> downloadURLs = new ArrayList<DownloadURL>();

    public Long getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(Long taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public String getSpeciesScientificName() {
        return speciesScientificName;
    }

    public void setSpeciesScientificName(String speciesScientificName) {
        this.speciesScientificName = speciesScientificName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public long getNumberOfSpectra() {
        return numberOfSpectra;
    }

    public void setNumberOfSpectra(long numberOfSpectra) {
        this.numberOfSpectra = numberOfSpectra;
    }

    public long getNumberOfPeptides() {
        return numberOfPeptides;
    }

    public void setNumberOfPeptides(long numberOfPeptides) {
        this.numberOfPeptides = numberOfPeptides;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public List<DownloadURL> getDownloadURLs() {
        return downloadURLs;
    }

    public void addDownloadURL(DownloadURL downloadURL) {
        downloadURLs.add(downloadURL);
    }
}
