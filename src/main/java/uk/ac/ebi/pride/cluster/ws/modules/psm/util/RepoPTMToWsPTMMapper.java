package uk.ac.ebi.pride.cluster.ws.modules.psm.util;

import uk.ac.ebi.pride.cluster.ws.modules.psm.model.Modification;
import uk.ac.ebi.pride.spectracluster.repo.model.ModificationDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Map from repository based PTM to web service based PTM
 *
 * @author Rui Wang
 * @version $Id$
 */
@Deprecated
public final class RepoPTMToWsPTMMapper {

    public static List<Modification> asPTMList(List<ModificationDetail> modificationDetails) {
        List<Modification> modifications = new ArrayList<Modification>();

        for (ModificationDetail modificationDetail : modificationDetails) {
            Modification modification = asPTM(modificationDetail);
            modifications.add(modification);
        }

        return modifications;
    }

    public static Modification asPTM(ModificationDetail modificationDetail) {
        Modification modification = new Modification();

        modification.setAccession(modificationDetail.getAccession());
        modification.setName(modificationDetail.getName());
        modification.setPosition(modificationDetail.getPosition());

        return modification;
    }
}
