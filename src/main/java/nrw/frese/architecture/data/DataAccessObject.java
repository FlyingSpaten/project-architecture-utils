package nrw.frese.architecture.data;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.model.KeyedObject;

public interface DataAccessObject<T extends KeyedObject<ID>, ID> extends CrudInterface<T, ID> {

}
