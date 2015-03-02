package uk.ac.ebi.pride.cluster.ws.modules.library.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Rui Wang
 * @version $Id$
 */
public class SpectralLibraryDownloadURLGenerator {

    @Value("#{spectralLibraryProperties['spectral.library.ftp.download.url.prefix']}")
    private String ftpUrlPrefix;

    @Value("#{spectralLibraryProperties['spectral.library.aspera.download.url.prefix']}")
    private String asperaUrlPrefix;

    public String asFtpURL(String version, String fileName) {
        return ftpUrlPrefix + "/" + version + "/" + fileName;
    }

    public String asAsperaURL(String version, String fileName) {
        return asperaUrlPrefix + "/" + version + "/" + fileName;
    }
}
