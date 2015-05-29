package uk.ac.ebi.pride.cluster.ws.modules.library.util;

import uk.ac.ebi.pride.cluster.ws.modules.library.model.DownloadURL;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectrumLibrary;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectrumLibraryRelease;
import uk.ac.ebi.pride.spectracluster.repo.model.SpectrumLibraryDetail;

import java.util.List;

/**
 * Mapper from repository spectrum library representation to web service spectrum library representation
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class RepoSpectrumLibraryToWsSpectrumLibraryMapper {

    public static SpectrumLibraryRelease asSpectrumLibraryRelease(List<SpectrumLibraryDetail> spectrumLibraryDetails, SpectrumLibraryDownloadURLGenerator generator) {
        SpectrumLibraryRelease spectrumLibraryRelease = new SpectrumLibraryRelease();

        SpectrumLibraryDetail spectrumLibraryDetail = spectrumLibraryDetails.get(0);
        spectrumLibraryRelease.setVersion(spectrumLibraryDetail.getVersion());
        spectrumLibraryRelease.setReleaseDate(spectrumLibraryDetail.getReleaseDate());

        for (SpectrumLibraryDetail detail : spectrumLibraryDetails) {
            SpectrumLibrary spectrumLibrary = asSpectrumLibrary(detail, generator);
            spectrumLibraryRelease.addSpectrumLibrary(spectrumLibrary);
        }

        return spectrumLibraryRelease;
    }

    public static SpectrumLibrary asSpectrumLibrary(SpectrumLibraryDetail spectrumLibraryDetail, SpectrumLibraryDownloadURLGenerator generator) {
        SpectrumLibrary spectrumLibrary = new SpectrumLibrary();

        spectrumLibrary.setNumberOfSpectra(spectrumLibraryDetail.getNumberOfSpectra());
        spectrumLibrary.setNumberOfPeptides(spectrumLibraryDetail.getNumberOfPeptides());
        spectrumLibrary.setTaxonomyId(spectrumLibraryDetail.getTaxonomyId());
        spectrumLibrary.setFileSize(spectrumLibraryDetail.getFileSize());
        spectrumLibrary.setSpeciesName(spectrumLibraryDetail.getSpeciesName());
        spectrumLibrary.setSpeciesScientificName(spectrumLibraryDetail.getSpeciesScientificName());

        String version = spectrumLibraryDetail.getVersion();
        String fileName = spectrumLibraryDetail.getFileName();

        // FTP
        DownloadURL ftpDownloadURL = new DownloadURL();
        ftpDownloadURL.setURL(generator.asFtpURL(version, fileName));
        ftpDownloadURL.setProtocol("FTP");
        spectrumLibrary.addDownloadURL(ftpDownloadURL);

        // ASPERA
        DownloadURL asperaDownloadURL = new DownloadURL();
        asperaDownloadURL.setURL(generator.asAsperaURL(version, fileName));
        asperaDownloadURL.setProtocol("ASPERA");
        spectrumLibrary.addDownloadURL(asperaDownloadURL);

        return spectrumLibrary;
    }
}
