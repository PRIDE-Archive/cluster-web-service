package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;
import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.Cluster;
import uk.ac.ebi.pride.cluster.ws.modules.cluster.model.Modification;
import uk.ac.ebi.pride.cluster.ws.modules.spectrum.model.Spectrum;
import uk.ac.ebi.pride.cluster.ws.modules.spectrum.model.SpectrumPeak;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusterSummary;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;
import uk.ac.ebi.pride.spectracluster.repo.utils.ModificationDetailFetcher;
import uk.ac.ebi.pridemod.model.PTM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public final class RepoClusterToWsClusterMapper {

    public static Spectrum asConsensusSpectrum(ClusterSummary repoCluster) {

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

    public static Cluster asCluster(ClusterSummary repoClusterSummary, ClusteredPSMDetail repoPSM, ModificationDetailFetcher modificationDetailFetcher) {
        Cluster newCluster = new Cluster();

        newCluster.setId(repoClusterSummary.getId());
        newCluster.setAveragePrecursorCharge(repoClusterSummary.getAveragePrecursorCharge());
        newCluster.setAveragePrecursorMz(repoClusterSummary.getAveragePrecursorMz());
        newCluster.setMaxRatio(repoClusterSummary.getMaxPeptideRatio());
        newCluster.setSequence(repoPSM.getSequence());
        newCluster.setClusterQuality(repoClusterSummary.getQuality().toString());

        // count numbers
        newCluster.setNumberOfSpectra(repoClusterSummary.getNumberOfSpectra());
        newCluster.setTotalNumberOfSpectra(repoClusterSummary.getTotalNumberOfSpectra());
        newCluster.setNumberOfSpecies(repoClusterSummary.getNumberOfSpecies());
        newCluster.setTotalNumberOfSpecies(repoClusterSummary.getTotalNumberOfSpecies());
        newCluster.setNumberOfModifications(repoClusterSummary.getNumberOfModifications());
        newCluster.setTotalNumberOfModifications(repoClusterSummary.getTotalNumberOfModifications());
        newCluster.setNumberOfProjects(repoClusterSummary.getNumberOfProjects());
        newCluster.setTotalNumberOfProjects(repoClusterSummary.getTotalNumberOfProjects());

        // modifications
        List<ModificationProvider> modifications = asModifications(repoPSM.getPsmDetail().getModifications(), modificationDetailFetcher);
        newCluster.addModifications(modifications);

        return newCluster;
    }

    public static List<ModificationProvider> asModifications(List<ModificationProvider> repoMods,
                                                     ModificationDetailFetcher modificationDetailFetcher) {
        List<ModificationProvider> mods = new ArrayList<ModificationProvider>();

        if (repoMods != null) {
            for (ModificationProvider repoMod : repoMods) {
                Modification modification = new Modification();
                modification.setAccession(repoMod.getAccession());
                modification.setMainPosition(repoMod.getMainPosition());
                modification.setName(repoMod.getName());
                modification.setNeutralLoss(repoMod.getNeutralLoss());
                modification.setPositionMap(new HashMap<Integer, CvParamProvider>(repoMod.getPositionMap()));

                // mono mass
                PTM ptm = modificationDetailFetcher.getPTMbyAccession(repoMod.getAccession());
                if (ptm != null) {
                    modification.setMonoMass(ptm.getMonoDeltaMass());
                }

                mods.add(modification);
            }
        }

        return mods;
    }
}
