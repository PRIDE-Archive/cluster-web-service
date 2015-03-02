package uk.ac.ebi.pride.cluster.ws.modules.library.util;

import uk.ac.ebi.pride.cluster.ws.modules.library.model.DownloadURL;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectralLibrary;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectralLibraryRelease;
import uk.ac.ebi.pride.spectracluster.repo.model.SpectralLibraryDetail;

import java.util.List;

/**
 * Mapper from repository spectral library representation to web service spectral library representation
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class RepoSpectralLibraryToWsSpectralLibraryMapper {

    public static SpectralLibraryRelease asSpectralLibraryRelease(List<SpectralLibraryDetail> spectralLibraryDetails, SpectralLibraryDownloadURLGenerator generator) {
        SpectralLibraryRelease spectralLibraryRelease = new SpectralLibraryRelease();

        SpectralLibraryDetail spectralLibraryDetail = spectralLibraryDetails.get(0);
        spectralLibraryRelease.setVersion(spectralLibraryDetail.getVersion());
        spectralLibraryRelease.setReleaseDate(spectralLibraryDetail.getReleaseDate());

        for (SpectralLibraryDetail detail : spectralLibraryDetails) {
            SpectralLibrary spectralLibrary = asSpectralLibrary(detail, generator);
            spectralLibraryRelease.addSpectralLibrary(spectralLibrary);
        }

        return spectralLibraryRelease;
    }

    public static SpectralLibrary asSpectralLibrary(SpectralLibraryDetail spectralLibraryDetail, SpectralLibraryDownloadURLGenerator generator) {
        SpectralLibrary spectralLibrary = new SpectralLibrary();

        spectralLibrary.setNumberOfSpectra(spectralLibraryDetail.getNumberOfSpectra());
        spectralLibrary.setTaxonomyId(spectralLibraryDetail.getTaxonomyId());
        spectralLibrary.setFileSize(spectralLibraryDetail.getFileSize());
        spectralLibrary.setSpeciesName(spectralLibraryDetail.getSpeciesName());
        spectralLibrary.setSpeciesScientificName(spectralLibraryDetail.getSpeciesScientificName());

        String version = spectralLibraryDetail.getVersion();
        String fileName = spectralLibraryDetail.getFileName();

        // FTP
        DownloadURL ftpDownloadURL = new DownloadURL();
        ftpDownloadURL.setURL(generator.asFtpURL(version, fileName));
        ftpDownloadURL.setProtocol("FTP");

        // ASPERA
        DownloadURL asperaDownloadURL = new DownloadURL();
        asperaDownloadURL.setURL(generator.asAsperaURL(version, fileName));
        asperaDownloadURL.setProtocol("ASPERA");

        return spectralLibrary;
    }
}
