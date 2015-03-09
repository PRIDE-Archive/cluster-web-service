package uk.ac.ebi.pride.cluster.ws.modules.psm.util;

import uk.ac.ebi.pride.cluster.ws.modules.psm.model.Modification;
import uk.ac.ebi.pride.spectracluster.repo.model.PTMDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Map from repository based PTM to web service based PTM
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class RepoPTMToWsPTMMapper {

    public static List<Modification> asPTMList(List<PTMDetail> ptmDetails) {
        List<Modification> modifications = new ArrayList<Modification>();

        for (PTMDetail ptmDetail : ptmDetails) {
            Modification modification = asPTM(ptmDetail);
            modifications.add(modification);
        }

        return modifications;
    }

    public static Modification asPTM(PTMDetail ptmDetail) {
        Modification modification = new Modification();

        modification.setAccession(ptmDetail.getAccession());
        modification.setName(ptmDetail.getName());
        modification.setPosition(ptmDetail.getPosition());

        return modification;
    }
}
