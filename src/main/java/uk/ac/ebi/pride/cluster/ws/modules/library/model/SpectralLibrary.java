package uk.ac.ebi.pride.cluster.ws.modules.library.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Details of one spectral library, including download links
 *
 * @author Rui Wang
 * @version $Id$
 */
public class SpectralLibrary {
    private String taxonomyId;
    private String speciesName;
    private long numberOfSpectra;
    private float fileSize;
    private final List<DownloadURL> downloadURLs = new ArrayList<DownloadURL>();

    public String getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(String taxonomyId) {
        this.taxonomyId = taxonomyId;
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

    public float getFileSize() {
        return fileSize;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public List<DownloadURL> getDownloadURLs() {
        return downloadURLs;
    }

    public void addDownloadURL(DownloadURL downloadURL) {
        downloadURLs.add(downloadURL);
    }
}
