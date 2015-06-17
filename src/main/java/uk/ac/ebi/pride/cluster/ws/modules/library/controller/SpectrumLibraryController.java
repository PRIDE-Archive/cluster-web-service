package uk.ac.ebi.pride.cluster.ws.modules.library.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectrumLibraryRelease;
import uk.ac.ebi.pride.cluster.ws.modules.library.util.RepoSpectrumLibraryToWsSpectrumLibraryMapper;
import uk.ac.ebi.pride.cluster.ws.modules.library.util.SpectrumLibraryDownloadURLGenerator;
import uk.ac.ebi.pride.spectracluster.repo.dao.library.SpectrumLibraryReader;
import uk.ac.ebi.pride.spectracluster.repo.model.SpectrumLibraryDetail;

import java.util.List;

/**
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "spectrumLibrary", description = "retrieve information about spectrum libraries", position = 0)
@Controller
@RequestMapping(value = "/spectrumLibrary")
public class SpectrumLibraryController {

    private static final Logger logger = LoggerFactory.getLogger(SpectrumLibraryController.class);

    @Autowired
    private SpectrumLibraryReader spectrumLibraryReader;

    @Autowired
    private SpectrumLibraryDownloadURLGenerator spectrumLibraryDownloadURLGenerator;

    @ApiOperation(value = "returns spectrum libraries of the latest release", position = 1, notes = "retrieve spectrum libraries of the latest release")
    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    SpectrumLibraryRelease getLatestSpectrumLibraryRelease() {
        logger.info("The latest spectrum library release requested");

        List<SpectrumLibraryDetail> latestSpectrumLibraries = spectrumLibraryReader.getLatestSpectrumLibraries();

        return RepoSpectrumLibraryToWsSpectrumLibraryMapper.asSpectrumLibraryRelease(latestSpectrumLibraries, spectrumLibraryDownloadURLGenerator);
    }

}
