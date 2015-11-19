package uk.ac.ebi.pride.cluster.ws.modules.result.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.ac.ebi.pride.cluster.ws.modules.result.model.DownloadURL;
import uk.ac.ebi.pride.cluster.ws.modules.result.model.ResultFile;
import uk.ac.ebi.pride.cluster.ws.modules.result.model.ResultFilesRelease;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "result", description = "retrieve information about result files", position = 0)
@Controller
@RequestMapping(value = "/result")
public class ResultFilesController {

    private static final Logger logger = LoggerFactory.getLogger(ResultFilesController.class);

//    @Autowired
//    private SpectrumLibraryReader spectrumLibraryReader;
//
//    @Autowired
//    private SpectrumLibraryDownloadURLGenerator spectrumLibraryDownloadURLGenerator;

    /**
     * Todo from now this is hard-code in the web service in the future should be store in the database
     */

    @ApiOperation(value = "Endpoint that returns result files of the latest release", position = 1, notes = "retrieve result files of the latest release")
    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    ResultFilesRelease getLatestSpectrumLibraryRelease(){
        logger.info("The latest spectrum library release requested");

        List<ResultFile> latestResultFiles = new ArrayList<ResultFile>();
        latestResultFiles.add(resultFile("Unidentified Human Clusters","Unidentified human clusters with at least 100 spectra","ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_human_id0_s100.mgf.gz", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_human_id0_s100.mzML.gz"));
        latestResultFiles.add(resultFile("Unidentified Clusters", "Unidentified Clusters with at least 100 spectra", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_id0_s100.mgf.gz", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_id0_s100.mzML.gz"));
        latestResultFiles.add(resultFile("Human Phospho clusters", "Human Phospho clusters with at least 3 identified spectra and a minimum ratio of 70%", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_phospho_id3_rh07_human.mgf.gz", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_phospho_id3_rh07_human.mzML.gz"));
        latestResultFiles.add(resultFile("Phospho clusters", "Phospho clusters with at least 3 identified spectra and a minimum ratio of 70%", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_phospho_id3_rh07.mgf.gz", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_phospho_id3_rh07.mzML.gz"));
        latestResultFiles.add(resultFile("Unidentified Clusters", "Clusters with at at least 10 spectra and a ratio < 50%", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_id10_mr05.mgf.gz", "ftp://ftp.pride.ebi.ac.uk/pride/data/cluster/result-files/latest/results_id10_mr05.mzML.gz"));

        return new ResultFilesRelease("2015-04", "2015-04-30", latestResultFiles);
    }

    private ResultFile resultFile(String name, String description, String mgf, String mzML){

        List<DownloadURL> resultURLs = new ArrayList<DownloadURL>();

        resultURLs.add(new DownloadURL(mgf, "MGF"));
        resultURLs.add(new DownloadURL(mzML, "mzML"));

        return new ResultFile(name, description, resultURLs);


    }

}
