package io.khaminfo.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.khaminfo.ppmtool.domain.Project;
import io.khaminfo.ppmtool.services.MapValidationError;
import io.khaminfo.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private MapValidationError mapValidationError;
	
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project ,BindingResult result){
		Map<String, String> errorMap = mapValidationError.mapValidationerror(result);
		
		if (errorMap != null) 
			return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
		
		
		Project proj = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		
		Project project =  projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project , HttpStatus.OK);
	}
	@GetMapping("/all")
	public Iterable<Project> getAllProjedt(){return projectService.findAllProject();}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject (@PathVariable String projectId){
		projectService.deletePrjectByIdentifier(projectId);
		return new ResponseEntity<String>("The project with id : ' "+projectId+"' was deleted",HttpStatus.OK);
	}
}
