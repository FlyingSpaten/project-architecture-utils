package nrw.frese.architecture.service;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.data.DataAccessObject;
import nrw.frese.architecture.model.KeyedObject;

import java.util.List;

public interface SingleObjectServiceService<T extends KeyedObject<ID>, ID> extends CrudInterface<T, ID> {

    DataAccessObject<T, ID> getDataAccessObject();

    @Override
    public default T add(T entity) {
        return getDataAccessObject().add(entity);
    }

    @Override
    public default T update(T entity) {
        return getDataAccessObject().update(entity);
    }

    @Override
    public default T get(ID id) {
        return getDataAccessObject().get(id);
    }

    @Override
    public default List<T> getAll() {
        return getDataAccessObject().getAll();
    }

    @Override
    public default void delete(ID id) {
        getDataAccessObject().delete(id);
    }

    @Override
    public default void delete(T entity) {
        getDataAccessObject().delete(entity);
    }
}
