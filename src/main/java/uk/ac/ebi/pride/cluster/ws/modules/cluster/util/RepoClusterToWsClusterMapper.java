package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.cluster.ws.modules.spectrum.model.Spectrum;
import uk.ac.ebi.pride.cluster.ws.modules.spectrum.model.SpectrumPeak;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterDetail;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class RepoClusterToWsClusterMapper {

    public static Spectrum getConsensusSpectrum(ClusterSummary repoCluster) {

        Spectrum consensusSpectrum = new Spectrum();

        consensusSpectrum.setClusterId(repoCluster.getId());
        String[] peaksMz = repoCluster.getConsensusSpectrumMz().split(",");
        String[] peaksIntensities = repoCluster.getConsensusSpectrumIntensity().split(",");
        int i = 0;
        consensusSpectrum.setPeaks(new LinkedList<SpectrumPeak>());
        for (String peak : peaksMz) {
            SpectrumPeak newPeak = new SpectrumPeak();
            newPeak.setMz(Double.parseDouble(peak));
            newPeak.setIntensity(Double.parseDouble(peaksIntensities[i]));
            i++;
            consensusSpectrum.getPeaks().add(newPeak);
        }
        consensusSpectrum.setMzStart(consensusSpectrum.getPeaks().get(0).getMz());
        consensusSpectrum.setMzStop(consensusSpectrum.getPeaks().get(consensusSpectrum.getPeaks().size() - 1).getMz());

        return consensusSpectrum;
    }
}
