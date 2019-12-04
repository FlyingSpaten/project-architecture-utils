package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.model.KeyedObject;

public interface ObjectConverter<DTO extends KeyedObject<ID>, DB extends KeyedObject<ID>, ID> {

    public DTO convertToDto(DB dbEntity);

    public DB convertToDb(DTO dtoEntity);

}
