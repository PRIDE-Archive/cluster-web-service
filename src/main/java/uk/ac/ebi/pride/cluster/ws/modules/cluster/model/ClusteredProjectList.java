package uk.ac.ebi.pride.cluster.ws.modules.cluster.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A list of clustered projects
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ClusteredProjectList {

    private final List<ClusteredProject> clusteredProjects = new ArrayList<ClusteredProject>();

    public List<ClusteredProject> getClusteredProjects() {
        return this.clusteredProjects;
    }

    public void addClusteredProject(ClusteredProject clusteredProject) {
        this.clusteredProjects.add(clusteredProject);
    }

    public void addClusteredProjects(Collection<ClusteredProject> clusteredPeptides) {
        this.clusteredProjects.addAll(clusteredPeptides);
    }
}
