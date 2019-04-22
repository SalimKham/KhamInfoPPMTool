package io.khaminfo.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.khaminfo.ppmtool.domain.Backlog;
import io.khaminfo.ppmtool.domain.ProjectTask;
import io.khaminfo.ppmtool.exceptions.ProjectIdException;
import io.khaminfo.ppmtool.exceptions.ProjectNotFoundException;
import io.khaminfo.ppmtool.repositories.BacklogRepository;
import io.khaminfo.ppmtool.repositories.ProjectRepository;
import io.khaminfo.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

		try {

			// PTs to be added to a specific project, project != null, BL exists
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
			// set the bl to pt
			projectTask.setBacklog(backlog);
			// we want our project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101
			Integer BacklogSequence = backlog.getPTSequence();
			// Update the BL SEQUENCE
			BacklogSequence++;
			backlog.setPTSequence(BacklogSequence);

			// Add Sequence to Project Task
			projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);

			// INITIAL priority when priority null
			if (projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
				projectTask.setPriority(3);
			}
			// INITIAL status when status is null
			if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
				projectTask.setStatus("TO_DO");
			}

			return projectTaskRepository.save(projectTask);
		} catch (Exception e) {
			throw new ProjectNotFoundException("Project not found.");
		}
	}

	public List<ProjectTask> findBacklogById(String backlog_id) {
		
		if (backlogRepository.findByProjectIdentifier(backlog_id) == null) {
			throw new ProjectNotFoundException("Project with id : (" + backlog_id + ") does not exist.");
		}

		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);

	}

	public ProjectTask updateProjectTask(ProjectTask projectTask) {

		try {
			return projectTaskRepository.save(projectTask);
		} catch (Exception e) {
			throw new ProjectNotFoundException(e.getMessage());
		}
	}

	public ProjectTask findPTByProjectSequence(String projectIdentifier, String projectSequence) {
		ProjectTask projectTask = projectTaskRepository.findByProjectIdentifierAndProjectSequence(projectIdentifier,
				projectSequence);
		if (projectTask == null) {
			throw new ProjectNotFoundException("Project not found.");
		}
		return projectTask;
	}

	public void deleteProjectBySequence(String projectid) {

		try {
			projectTaskRepository.deleteProjectByProjectIdentifier(projectid);

		} catch (Exception e) {
			throw new ProjectIdException("Cannot Project with ID '" + projectid + "'. This project does not exist");
		}
	}

}
