package nrw.frese.architecture.spring;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.exception.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.List;

public interface SingleObjectSpringDataService<T extends KeyedObject<ID>, ID> extends CrudInterface<T, ID> {

    CrudRepository<T, ID> getDataAccessObject();

    Class<T> getServiceClass();

    @Override
    public default T add(T entity) {
        return getDataAccessObject().save(entity);
    }

    @Override
    public default T update(T entity) {
        return getDataAccessObject().save(entity);
    }

    @Override
    public default T get(ID id) {
        return getDataAccessObject().findById(id).orElseThrow(() -> new EntityNotFoundException(getServiceClass(), id));
    }

    @Override
    public default List<T> getAll() {
        List<T> allElements = new LinkedList<>();
        getDataAccessObject().findAll().iterator().forEachRemaining(allElements::add);
        return allElements;
    }

    @Override
    public default void deleteById(ID id) {
        getDataAccessObject().deleteById(id);
    }

    @Override
    public default void delete(T entity) {
        getDataAccessObject().delete(entity);
    }

}
