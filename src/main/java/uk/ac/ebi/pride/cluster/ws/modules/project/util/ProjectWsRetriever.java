package uk.ac.ebi.pride.cluster.ws.modules.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.pride.archive.web.service.model.project.ProjectDetail;
import uk.ac.ebi.pride.cluster.ws.modules.project.model.Project;
import uk.ac.ebi.pride.cluster.ws.modules.project.model.ProjectList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Retriever on project details using PRIDE Archive web service
 *
 * @author Rui Wang
 * @version $Id$
 */
public final class ProjectWsRetriever {

    private static final Logger logger = LoggerFactory.getLogger(ProjectWsRetriever.class);

    // fixed sized thread pool that run 20 threads at a time
    private final ExecutorService threadPool = Executors.newFixedThreadPool(20);

    private final RestTemplate restTemplate = new RestTemplate();

    private final String projectWsUrl;

    public ProjectWsRetriever(String projectWsUrl) {
        this.projectWsUrl = projectWsUrl;
    }


    public ProjectList getProjects(Collection<String> projectAccessions) {

        CountDownLatch countDown = new CountDownLatch(projectAccessions.size());


        ProjectList projectList = new ProjectList();

        for (String projectAccession : projectAccessions) {
            RetrieveProjectDetailTask retrieveProjectDetailTask = new RetrieveProjectDetailTask(countDown, projectAccession, projectList);
            threadPool.submit(retrieveProjectDetailTask);
        }

        try {
            countDown.await();
        } catch (InterruptedException e) {
            logger.warn("Retrieving project details was interrupted", e);
        } finally {
            threadPool.shutdown();
        }

        return projectList;
    }

    private class RetrieveProjectDetailTask implements Runnable {

        private final CountDownLatch countDown;
        private final String projectAccession;
        private final ProjectList projectList;

        public RetrieveProjectDetailTask(CountDownLatch countDown, String projectAccession, ProjectList projectList) {
            this.countDown = countDown;
            this.projectAccession = projectAccession;
            this.projectList = projectList;
        }

        @Override
        public void run() {
            try {
                Project project = getProject(projectAccession);
                projectList.addProject(project);
            } finally {
                countDown.countDown();
            }
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



}
