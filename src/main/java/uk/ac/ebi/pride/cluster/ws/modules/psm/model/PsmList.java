package uk.ac.ebi.pride.cluster.ws.modules.psm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A list of psms
 *
 * @author Rui Wang
 * @version $Id$
 */
public class PsmList {
    private final List<Psm> psms = new ArrayList<Psm>();

    public List<Psm> getPsms() {
        return psms;
    }

    public void addPsm(Psm psm) {
        psms.add(psm);
    }

    public void addPsms(Collection<Psm> psms) {
        psms.addAll(psms);
    }
}
