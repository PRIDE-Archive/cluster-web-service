package uk.ac.ebi.pride.cluster.ws.modules.library.model;

/**
 * Spectrum library download URL
 *
 * @author Rui Wang
 * @version $Id$
 */
public class DownloadURL {
    private String protocol;
    private String URL;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
