package io.khaminfo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.khaminfo.ppmtool.domain.Project;
import io.khaminfo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdate(Project project) {
		return projectRepository.save(project);
	}

}
