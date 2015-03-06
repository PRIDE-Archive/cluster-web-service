package uk.ac.ebi.pride.cluster.ws.modules.library.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A release of all the spectral libraries
 *
 * @author Rui Wang
 * @version $Id$
 */
public class SpectrumLibraryRelease {
    private String version;
    private Date releaseDate;
    private final List<SpectrumLibrary> spectralLibraries = new ArrayList<SpectrumLibrary>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<SpectrumLibrary> getSpectralLibraries() {
        return spectralLibraries;
    }

    public void addSpectralLibrary(SpectrumLibrary spectrumLibrary) {
        this.spectralLibraries.add(spectrumLibrary);
    }
}
