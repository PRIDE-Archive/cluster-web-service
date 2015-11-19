package uk.ac.ebi.pride.cluster.ws.modules.result.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A release of all the spectrum libraries
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ResultFilesRelease {

    private String version;

    private String releaseDate;

    private List<ResultFile> resultFiles = new ArrayList<ResultFile>();

    public ResultFilesRelease(String version, String releaseDate, List<ResultFile> resultFiles) {
        this.version = version;
        this.releaseDate = releaseDate;
        this.resultFiles = resultFiles;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<ResultFile> getResultFiles() {
        return resultFiles;
    }

    public void setResultFiles(List<ResultFile> resultFiles) {
        this.resultFiles = resultFiles;
    }
}
