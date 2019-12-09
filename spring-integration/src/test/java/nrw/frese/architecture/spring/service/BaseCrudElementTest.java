package nrw.frese.architecture.spring.service;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.exception.EntityNotFoundException;
import org.junit.Test;

public abstract class BaseCrudElementTest<T extends KeyedObject<Long>> {

    @Test
    public void testAdd() {
        T object = getNewObject();
        assert object.getId() == null || object.getId() == 0;
        object = getCrudElement().add(object);
        assert object.getId() > 0;
    }

    @Test
    public void testUpdate() {
        T object = getNewObject();
        assert object.getId() == null || object.getId() == 0;
        object = getCrudElement().add(object);
        assert object.getId() > 0;
        object = modifyObject(object);
        T updated = getCrudElement().update(object);
        assert isModified(updated);
    }

    @Test
    public void testGet() {
        T object = getNewObject();
        object = modifyObject(object);
        object = getCrudElement().add(object);
        assert object.getId() != null && isModified(object);
        T loaded = getCrudElement().get(object.getId());
        assert loaded.equals(object);
    }

    @Test
    public void testGetAll() {
        int firstSize = getCrudElement().getAll().size();
        getCrudElement().add(getNewObject());
        assert getCrudElement().getAll().size() == firstSize + 1;
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteById() {
        T object = getCrudElement().add(getNewObject());
        assert getCrudElement().get(object.getId()).equals(object);
        getCrudElement().deleteById(object.getId());
        getCrudElement().get(object.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteByEntity() {
        T object = getCrudElement().add(getNewObject());
        assert getCrudElement().get(object.getId()).equals(object);
        getCrudElement().delete(object);
        getCrudElement().get(object.getId());
    }

    protected abstract T getNewObject();

    protected abstract T modifyObject(T object);

    protected abstract boolean isModified(T object);

    protected abstract CrudInterface<T, Long> getCrudElement();

}
