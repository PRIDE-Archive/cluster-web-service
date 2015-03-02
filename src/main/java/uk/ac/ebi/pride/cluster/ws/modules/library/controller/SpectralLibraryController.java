package uk.ac.ebi.pride.cluster.ws.modules.library.controller;

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
import uk.ac.ebi.pride.cluster.ws.modules.library.model.DownloadURL;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectralLibrary;
import uk.ac.ebi.pride.cluster.ws.modules.library.model.SpectralLibraryRelease;

import java.util.Date;

/**
 * @author Rui Wang
 * @version $Id$
 */
@Api(value = "library", description = "retrieve information about spectral libraries", position = 0)
@Controller
@RequestMapping(value = "/library")
public class SpectralLibraryController {

    private static final Logger logger = LoggerFactory.getLogger(SpectralLibraryController.class);

    @ApiOperation(value = "returns PSMs for a given Cluster ID", position = 1, notes = "retrieve PSMs for a given Cluster ID")
    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public
    @ResponseBody
    SpectralLibraryRelease getLatestSpectralLibraryRelease() {
        logger.info("Spectral library releases requested");

        //todo: hardcoded spectral libraries at the moment, need to refactor this into a more generic solution

        SpectralLibraryRelease spectralLibraryRelease = new SpectralLibraryRelease();

        spectralLibraryRelease.setVersion("1.0.0");
        spectralLibraryRelease.setReleaseDate(new Date(System.currentTimeMillis()));

        SpectralLibrary spectralLibrary = new SpectralLibrary();
        spectralLibrary.setTaxonomyId(9606l);
        spectralLibrary.setSpeciesName("Homo sapiens (Human)");
        spectralLibrary.setHumanReadableSpeciesName("Human");
        spectralLibrary.setNumberOfSpectra(10000);
        spectralLibrary.setFileSize(10000);
        DownloadURL downloadURL = new DownloadURL();
        downloadURL.setProtocol("FTP");
        downloadURL.setURL("ftp://www.ebi.ac.uk/pride/cluster/download/library-1.msp");
        spectralLibrary.addDownloadURL(downloadURL);
        downloadURL = new DownloadURL();
        downloadURL.setProtocol("ASPERA");
        downloadURL.setURL("aspera://www.ebi.ac.uk/pride/cluster/download/library-1.msp");
        spectralLibrary.addDownloadURL(downloadURL);

        spectralLibraryRelease.addSpectralLibrary(spectralLibrary);

        spectralLibrary = new SpectralLibrary();
        spectralLibrary.setTaxonomyId(10090l);
        spectralLibrary.setSpeciesName("Mus musculus (Mouse)");
        spectralLibrary.setHumanReadableSpeciesName("Mouse");
        spectralLibrary.setNumberOfSpectra(1234567);
        spectralLibrary.setFileSize(50000);
        downloadURL = new DownloadURL();
        downloadURL.setProtocol("FTP");
        downloadURL.setURL("ftp://www.ebi.ac.uk/pride/cluster/download/library-2.msp");
        spectralLibrary.addDownloadURL(downloadURL);
        downloadURL = new DownloadURL();
        downloadURL.setProtocol("ASPERA");
        downloadURL.setURL("aspera://www.ebi.ac.uk/pride/cluster/download/library-2.msp");
        spectralLibrary.addDownloadURL(downloadURL);

        spectralLibraryRelease.addSpectralLibrary(spectralLibrary);

        return spectralLibraryRelease;
    }
}
