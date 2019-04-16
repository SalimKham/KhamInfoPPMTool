package io.khaminfo.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.khaminfo.ppmtool.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository  extends CrudRepository<ProjectTask, Long>{

}
