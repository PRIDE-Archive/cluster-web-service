package uk.ac.ebi.pride.cluster.ws.modules.project.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A list of PRIDE Archive project details
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ProjectList {
    // concurrent set for adding
    private final Set<Project> projects = Collections.synchronizedSet(new LinkedHashSet<Project>());

    public Set<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }
}
