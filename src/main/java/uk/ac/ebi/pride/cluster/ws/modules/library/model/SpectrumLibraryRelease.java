package uk.ac.ebi.pride.cluster.ws.modules.library.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A release of all the spectrum libraries
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

    public List<SpectrumLibrary> getSpectrumLibraries() {
        return spectralLibraries;
    }

    public void addSpectrumLibrary(SpectrumLibrary spectrumLibrary) {
        this.spectralLibraries.add(spectrumLibrary);
    }
}
