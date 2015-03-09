package uk.ac.ebi.pride.cluster.ws.modules.project.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.pride.archive.web.service.model.project.ProjectDetail;
import uk.ac.ebi.pride.cluster.ws.modules.project.model.Project;
import uk.ac.ebi.pride.cluster.ws.modules.project.model.ProjectList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Retriever on project details using PRIDE Archive web service
 *
 * @author Rui Wang
 * @version $Id$
 */
@Component
public final class ProjectWsRetriever {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("#{archiveProperties['archive.project.detail.url']}")
    private String projectWsUrl;

    public ProjectList getProjects(Collection<String> projectAccessions) {
        ProjectList projectList = new ProjectList();

        for (String projectAccession : projectAccessions) {
            Project project = getProject(projectAccession);
            projectList.addProject(project);
        }

        return projectList;
    }


    public Project getProject(String projectAccession) {
        ProjectDetail projectDetail = restTemplate.getForObject(projectWsUrl, ProjectDetail.class, projectAccession);

        if (projectDetail == null)
            return null;

        Project project = new Project();

        project.setAccession(projectDetail.getAccession());
        project.setTitle(projectDetail.getTitle());
        project.setDescription(projectDetail.getProjectDescription());

        Set<String> species = projectDetail.getSpecies();
        if (species != null) {
            project.setSpecies(new ArrayList<String>(species));
        }

        Set<String> tissues = projectDetail.getTissues();
        if (tissues != null) {
            project.setTissues(new ArrayList<String>(tissues));
        }

        Set<String> instrumentNames = projectDetail.getInstrumentNames();
        if (instrumentNames != null) {
            project.setInstruments(new ArrayList<String>(instrumentNames));
        }

        Set<String> experimentTypes = projectDetail.getExperimentTypes();
        if (experimentTypes != null) {
            project.setExperimentTypes(new ArrayList<String>(experimentTypes));
        }

        Set<String> projectTags = projectDetail.getProjectTags();
        if (projectTags != null) {
            project.setTags(new ArrayList<String>(projectTags));
        }

        return project;
    }
}
