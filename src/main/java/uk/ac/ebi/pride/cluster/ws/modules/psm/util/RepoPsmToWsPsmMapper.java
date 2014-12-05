package uk.ac.ebi.pride.cluster.ws.modules.psm.util;

import uk.ac.ebi.pride.cluster.ws.modules.psm.model.Psm;
import uk.ac.ebi.pride.spectracluster.repo.model.ClusteredPSMDetail;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class RepoPsmToWsPsmMapper {

    public static List<Psm> asPsmList(List<ClusteredPSMDetail> repoPsms) {
        List<Psm> res = new LinkedList<Psm>();

        for (ClusteredPSMDetail repoPsm: repoPsms) {
            Psm psm = new Psm();

            psm.setId(repoPsm.getPsmId());
            psm.setClusterId(repoPsm.getClusterId());
            psm.setSequence(repoPsm.getSequence());
            psm.setRatio(repoPsm.getPsmRatio());
            psm.setSpectrumId(repoPsm.getSpectrumId());

            res.add(psm);
        }

        return res;
    }


}
