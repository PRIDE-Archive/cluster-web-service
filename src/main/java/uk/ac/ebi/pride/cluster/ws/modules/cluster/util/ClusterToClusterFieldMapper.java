package uk.ac.ebi.pride.cluster.ws.modules.cluster.util;

import uk.ac.ebi.pride.cluster.search.model.ClusterFields;

/**
 * @author Rui Wang
 * @version $Id$
 */
public final class ClusterToClusterFieldMapper {

    private ClusterToClusterFieldMapper() {};


    public static String mapToClusterField(String fieldName) {
        if ("id".equals(fieldName)) {
            return ClusterFields.ID;
        }

        if ("averagePrecursorMz".equals(fieldName)) {
            return ClusterFields.AVG_PRECURSOR_MZ;
        }

        if ("averagePrecursorCharge".equals(fieldName)) {
            return ClusterFields.AVG_PRECURSOR_CHARGE;
        }

        if ("numberOfSpectra".equals(fieldName)) {
            return ClusterFields.NUMBER_OF_SPECTRA;
        }

        if ("totalNumberOfSpectra".equals(fieldName)) {
            return ClusterFields.TOTAL_NUMBER_OF_SPECTRA;
        }

        if ("numberOfSpecies".equals(fieldName)) {
            return ClusterFields.NUMBER_OF_SPECIES;
        }

        if ("totalNumberOfSpecies".equals(fieldName)) {
            return ClusterFields.TOTAL_NUMBER_OF_SPECIES;
        }

        if ("numberOfModifications".equals(fieldName)) {
            return ClusterFields.NUMBER_OF_MODIFICATIONS;
        }

        if ("totalNumberOfModifications".equals(fieldName)) {
            return ClusterFields.TOTAL_NUMBER_OF_MODIFICATIONS;
        }

        if ("numberOfProjects".equals(fieldName)) {
            return ClusterFields.NUMBER_OF_PROJECTS;
        }

        if ("totalNumberOfProjects".equals(fieldName)) {
            return ClusterFields.TOTAL_NUMBER_OF_PROJECTS;
        }

        if ("maxRatio".equals(fieldName)) {
            return ClusterFields.MAX_RATIO;
        }

        // this is questionable, since modifications are often mapped to multiple fields
        if ("modifications".equals(fieldName)) {
            return ClusterFields.MODIFICATIONS;
        }

        if ("sequence".equals(fieldName)) {
            return ClusterFields.HIGHEST_RATIO_PEP_SEQUENCE;
        }

        if ("sequence_sort".equals(fieldName)) {
            return ClusterFields.HIGHEST_RATIO_PEP_SEQUENCE_SORT;
        }

        if ("proteinAccession".equals(fieldName)) {
            return ClusterFields.HIGHEST_RATIO_PROTEIN_ACCESSION;
        }

        if ("clusterQuality".equals(fieldName)) {
            return ClusterFields.CLUSTER_QUALITY;
        }

        return null;
    }
}
