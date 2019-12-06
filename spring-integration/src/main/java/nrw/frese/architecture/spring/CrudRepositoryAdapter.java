package nrw.frese.architecture.spring;

import nrw.frese.architecture.data.DataRepository;
import nrw.frese.architecture.model.KeyedObject;
import org.springframework.data.repository.CrudRepository;

public interface CrudRepositoryAdapter<DB extends KeyedObject<ID>, ID> extends CrudRepository<DB, ID>, DataRepository<DB, ID> {
}
