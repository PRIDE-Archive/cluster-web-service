package uk.ac.ebi.pride.cluster.ws.modules.library.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Rui Wang
 * @version $Id$
 */
@Component
public class SpectrumLibraryDownloadURLGenerator {

    @Value("#{spectrumLibraryProperties['spectrum.library.ftp.download.url.prefix']}")
    private String ftpUrlPrefix;

    @Value("#{spectrumLibraryProperties['spectrum.library.aspera.download.url.prefix']}")
    private String asperaUrlPrefix;

    public String asFtpURL(String version, String fileName) {
        return ftpUrlPrefix + "/" + version + "/" + fileName;
    }

    public String asAsperaURL(String version, String fileName) {
        return asperaUrlPrefix + "/" + version + "/" + fileName;
    }
}
