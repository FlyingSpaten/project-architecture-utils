package nrw.frese.architecture.spring;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.converter.ObjectConverter;
import nrw.frese.architecture.service.exception.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.List;

public interface ConverterSpringDataService<DTO extends KeyedObject<ID>, DB extends KeyedObject<ID>, ID> extends CrudInterface<DTO, ID> {

    ObjectConverter<DTO, DB, ID> getConverter();

    CrudRepository<DB, ID> getDataAccessObject();

    Class<DB> getDbObjectClass();

    @Override
    public default DTO add(DTO entity) {
        return getConverter().convertToDto(getDataAccessObject().save(getConverter().convertToDb(entity)));
    }

    @Override
    public default DTO update(DTO entity) {
        return getConverter().convertToDto(getDataAccessObject().save(getConverter().convertToDb(entity)));
    }

    @Override
    public default DTO get(ID id) {
        return getConverter().convertToDto(getDataAccessObject().findById(id).orElseThrow(() -> new EntityNotFoundException(getDbObjectClass(), id)));
    }

    @Override
    public default List<DTO> getAll() {
        List<DTO> entities = new LinkedList<>();
        getDataAccessObject().findAll().iterator().forEachRemaining(db -> entities.add(getConverter().convertToDto(db)));
        return entities;
    }

    @Override
    public default void deleteById(ID id) {
        getDataAccessObject().deleteById(id);
    }

    @Override
    public default void delete(DTO entity) {
        getDataAccessObject().delete(getConverter().convertToDb(entity));
    }

}
