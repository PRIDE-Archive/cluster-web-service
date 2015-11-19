package uk.ac.ebi.pride.cluster.ws.modules.result.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Details of one spectrum library, including download links
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ResultFile {

    private String name;

    private String description;

    private List<DownloadURL> downloadURLs = new ArrayList<DownloadURL>();

    public ResultFile(String name, String description, List<DownloadURL> downloadURLs) {
        this.name = name;
        this.description = description;
        this.downloadURLs = downloadURLs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DownloadURL> getDownloadURLs() {
        return downloadURLs;
    }



}
