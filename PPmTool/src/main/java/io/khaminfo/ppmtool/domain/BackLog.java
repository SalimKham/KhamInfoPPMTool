package io.khaminfo.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BackLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer PTSquence;
	private String projectIdentifier;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="project_id" , nullable = false)
	@JsonIgnore
	private Project project;

	public BackLog() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPTSquence() {
		return PTSquence;
	}

	public void setPTSquence(Integer pTSquence) {
		PTSquence = pTSquence;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	

}
