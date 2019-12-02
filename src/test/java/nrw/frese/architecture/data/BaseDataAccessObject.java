package nrw.frese.architecture.data;

import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.model.TestObject;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDataAccessObject<T extends KeyedObject<Long>> implements DataAccessObject<T, Long> {

    private static long id = 1;

    public abstract List<T> getObjectList();

    public abstract void setObjectList(List<T> objectList);

    @Override
    public T add(T entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            entity.setId(id);
            id++;
            getObjectList().add(entity);
            return entity;
        } else {
            return update(entity);
        }
    }

    @Override
    public T update(T entity) {
        if (entity.getId() <= 0) {
            return add(entity);
        }
        return entity;
    }

    @Override
    public T get(Long aLong) {
        return getObjectList().stream().filter(testObject -> testObject.getId().equals(aLong)).findFirst().orElse(null);
    }

    @Override
    public List<T> getAll() {
        return new LinkedList<>(getObjectList());
    }

    @Override
    public void delete(Long aLong) {
        setObjectList(getObjectList().stream().filter(testObject -> !testObject.getId().equals(aLong)).collect(Collectors.toList()));
    }

    @Override
    public void delete(T entity) {
        setObjectList(getObjectList().stream().filter(testObject -> !testObject.equals(entity)).collect(Collectors.toList()));
    }

}
