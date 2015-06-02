package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import uk.ac.ebi.pride.archive.dataprovider.identification.ModificationProvider;
import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;

import java.util.Map;

/**
 * Modification for the web service
 *
 * @author Rui Wang
 * @version $Id$
 */
public class Modification implements ModificationProvider{

    private String accession;
    private String name;
    private Integer mainPosition;
    private Double monoMass;
    private CvParamProvider neutralLoss;
    private Map<Integer, CvParamProvider> positionMap;

    @Override
    public String getAccession() {
        return accession;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getMainPosition() {
        return mainPosition;
    }

    public Double getMonoMass() {
        return monoMass;
    }

    @Override
    public CvParamProvider getNeutralLoss() {
        return neutralLoss;
    }

    @Override
    public Map<Integer, CvParamProvider> getPositionMap() {
        return positionMap;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMainPosition(Integer mainPosition) {
        this.mainPosition = mainPosition;
    }

    public void setMonoMass(Double monoMass) {
        this.monoMass = monoMass;
    }

    public void setNeutralLoss(CvParamProvider neutralLoss) {
        this.neutralLoss = neutralLoss;
    }

    public void setPositionMap(Map<Integer, CvParamProvider> positionMap) {
        this.positionMap = positionMap;
    }
}
