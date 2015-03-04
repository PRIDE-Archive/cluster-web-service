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
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectralLibraryRelease;
import uk.ac.ebi.pride.cluster.ws.modules.library.util.RepoSpectralLibraryToWsSpectralLibraryMapper;
import uk.ac.ebi.pride.cluster.ws.modules.library.util.SpectralLibraryDownloadURLGenerator;
import uk.ac.ebi.pride.spectracluster.repo.dao.library.SpectralLibraryReader;
import uk.ac.ebi.pride.spectracluster.repo.model.SpectralLibraryDetail;

import java.util.List;

/**
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "library", description = "retrieve information about spectral libraries", position = 0)
@Controller
@RequestMapping(value = "/library")
public class SpectralLibraryController {

    private static final Logger logger = LoggerFactory.getLogger(SpectralLibraryController.class);

    @Autowired
    private SpectralLibraryReader spectralLibraryReader;

    @Autowired
    private SpectralLibraryDownloadURLGenerator spectralLibraryDownloadURLGenerator;

    @ApiOperation(value = "returns spectral libraries of the latest release", position = 1, notes = "retrieve spectral libraries of the latest release")
    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    SpectralLibraryRelease getLatestSpectralLibraryRelease() {
        logger.info("The latest spectral library release requested");

        List<SpectralLibraryDetail> latestSpectralLibraries = spectralLibraryReader.getLatestSpectralLibraries();

        return RepoSpectralLibraryToWsSpectralLibraryMapper.asSpectralLibraryRelease(latestSpectralLibraries, spectralLibraryDownloadURLGenerator);
    }

}
