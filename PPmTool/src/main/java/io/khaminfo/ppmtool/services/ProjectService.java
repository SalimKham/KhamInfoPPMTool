package io.khaminfo.ppmtool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.khaminfo.ppmtool.domain.Project;
import io.khaminfo.ppmtool.exceptions.ProjectIdException;
import io.khaminfo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }
    
    public Project findProjectByIdentifier(String idenfifier) {
    	Project pro =  projectRepository.findByProjectIdentifier(idenfifier);
    	if(pro == null) {
    		throw new ProjectIdException("project  '"+idenfifier+"' doesn't exist");
    		
    	}
    	return pro;
    }

    public Iterable<Project> findAllProject(){
    	return projectRepository.findAll();
    }
    public void deletePrjectByIdentifier(String identifier) {
    	Project project = projectRepository.findByProjectIdentifier(identifier);
    	if(project == null) {
    		throw new ProjectIdException("project  '"+identifier+"' doesn't exist");
    		
    	}
    	projectRepository.delete(project);
    	
    }
}
