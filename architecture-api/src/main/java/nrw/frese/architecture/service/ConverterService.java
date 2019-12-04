package nrw.frese.architecture.service;

import nrw.frese.architecture.data.DataRepository;
import nrw.frese.architecture.model.KeyedObject;
import nrw.frese.architecture.service.converter.ObjectConverter;
import nrw.frese.architecture.base.CrudInterface;

import java.util.List;
import java.util.stream.Collectors;

public interface ConverterService<DTO extends KeyedObject<ID>, DB extends KeyedObject<ID>, ID> extends CrudInterface<DTO, ID> {

    ObjectConverter<DTO, DB, ID> getConverter();

    DataRepository<DB, ID> getDataAccessObject();

    @Override
    public default DTO add(DTO entity) {
        return getConverter().convertToDto(getDataAccessObject().add(getConverter().convertToDb(entity)));
    }

    @Override
    public default DTO update(DTO entity) {
        return getConverter().convertToDto(getDataAccessObject().update(getConverter().convertToDb(entity)));
    }

    @Override
    public default DTO get(ID id) {
        return getConverter().convertToDto(getDataAccessObject().get(id));
    }

    @Override
    public default List<DTO> getAll() {
        return getDataAccessObject().getAll().stream().map(dbObject -> getConverter().convertToDto(dbObject)).collect(Collectors.toList());
    }

    @Override
    public default void delete(ID id) {
        getDataAccessObject().delete(id);
    }

    @Override
    public default void delete(DTO entity) {
        getDataAccessObject().delete(getConverter().convertToDb(entity));
    }
}
