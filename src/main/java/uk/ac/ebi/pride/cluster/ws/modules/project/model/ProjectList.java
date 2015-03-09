package uk.ac.ebi.pride.cluster.ws.modules.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of PRIDE Archive project details
 *
 * @author Rui Wang
 * @version $Id$
 */
public class ProjectList {
    private List<Project> projects = new ArrayList<Project>();

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }
}
