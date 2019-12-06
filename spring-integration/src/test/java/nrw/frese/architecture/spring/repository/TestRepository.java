package nrw.frese.architecture.spring.repository;

import nrw.frese.architecture.spring.CrudRepositoryAdapter;
import nrw.frese.architecture.spring.model.DbObject;

public interface TestRepository extends CrudRepositoryAdapter<DbObject, Long> {
}
