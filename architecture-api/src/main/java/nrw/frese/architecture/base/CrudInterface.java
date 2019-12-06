package nrw.frese.architecture.base;

import nrw.frese.architecture.model.KeyedObject;

import java.util.List;

public interface CrudInterface<T extends KeyedObject<ID>, ID> {

    public T add(T entity);

    public T update(T entity);

    public T get(ID id);

    public List<T> getAll();

    public void deleteById(ID id);

    public void delete(T entity);

}
