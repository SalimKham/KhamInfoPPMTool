package io.khaminfo.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.khaminfo.ppmtool.domain.BackLog;

@Repository
public interface BacklogRepository extends CrudRepository<BackLog, Long>{
  BackLog findByProjectIdentifier(String identifier);
}
