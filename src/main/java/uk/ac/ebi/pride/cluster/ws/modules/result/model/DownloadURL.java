package uk.ac.ebi.pride.cluster.ws.modules.result.model;

/**
 * Spectrum library download URL
 *
 * @author Rui Wang
 * @version $Id$
 */
public class DownloadURL {

    private String URL;
    private String type;

    public DownloadURL(String URL, String type) {
        this.URL = URL;
        this.type = type;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getType() {
        return type;
    }
}
