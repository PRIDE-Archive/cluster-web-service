package uk.ac.ebi.pride.cluster.ws.modules.psm.util;

import uk.ac.ebi.pride.cluster.ws.modules.psm.model.PTM;
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

    public static List<PTM> asPTMList(List<PTMDetail> ptmDetails) {
        List<PTM> ptms = new ArrayList<PTM>();

        for (PTMDetail ptmDetail : ptmDetails) {
            PTM ptm = asPTM(ptmDetail);
            ptms.add(ptm);
        }

        return ptms;
    }

    public static PTM asPTM(PTMDetail ptmDetail) {
        PTM ptm = new PTM();

        ptm.setAccession(ptmDetail.getAccession());
        ptm.setName(ptmDetail.getName());
        ptm.setPosition(ptmDetail.getPosition());

        return ptm;
    }
}
