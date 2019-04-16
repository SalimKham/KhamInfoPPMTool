package io.khaminfo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.khaminfo.ppmtool.domain.BackLog;
import io.khaminfo.ppmtool.domain.Project;
import io.khaminfo.ppmtool.exceptions.ProjectIdException;
import io.khaminfo.ppmtool.repositories.BacklogRepository;
import io.khaminfo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backLogRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			if (project.getId() == null) {
				BackLog backLog = new BackLog();
				project.setBacklog(backLog);
				backLog.setProject(project);
				backLog.setProjectIdentifier(project.getProjectIdentifier());
			}else {
				project.setBacklog(backLogRepository.findByProjectIdentifier(project.getProjectIdentifier()));
			}
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}

	}

	public Project findProjectByIdentifier(String projectId) {

		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Project ID '" + projectId + "' does not exist");

		}

		return project;
	}

	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}

	public void deleteProjectByIdentifier(String projectid) {
		Project project = projectRepository.findByProjectIdentifier(projectid.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Cannot Project with ID '" + projectid + "'. This project does not exist");
		}

		projectRepository.delete(project);
	}

}
