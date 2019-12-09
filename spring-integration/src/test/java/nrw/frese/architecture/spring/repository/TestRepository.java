package nrw.frese.architecture.spring.repository;

import nrw.frese.architecture.spring.model.DbObject;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<DbObject, Long> {
}
