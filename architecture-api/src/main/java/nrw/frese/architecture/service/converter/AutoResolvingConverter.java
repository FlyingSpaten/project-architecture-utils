package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.configuration.ConverterConfiguration;
import nrw.frese.architecture.model.KeyedObject;

public class AutoResolvingConverter<DTO extends KeyedObject<ID>, DB extends KeyedObject<ID>, ID> implements ObjectConverter<DTO, DB, ID> {

    @SuppressWarnings("unchecked")
    @Override
    public DTO convertToDto(DB dbEntity) {
        return (DTO) ConverterConfiguration.getCurrentInstance().getConverterByDatabaseObject(dbEntity).convertToDto(dbEntity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public DB convertToDb(DTO dtoEntity) {
        return (DB) ConverterConfiguration.getCurrentInstance().getConverterByTransitionObject(dtoEntity).convertToDb(dtoEntity);
    }

}
