package uk.ac.ebi.pride.cluster.ws.modules.psm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A list of quality aware psms
 *
 * @author Rui Wang
 * @version $Id$
 */
public class QualityAwarePsmList {

    private final List<Psm> qualityAwarePsms = new ArrayList<Psm>();

    public List<Psm> getQualityAwarePsms() {
        return qualityAwarePsms;
    }

    public void addQualityAwarePsm(QualityAwarePsm psm) {
        qualityAwarePsms.add(psm);
    }

    public void addQualityAwarePsms(Collection<QualityAwarePsm> psms) {
        psms.addAll(psms);
    }
}
