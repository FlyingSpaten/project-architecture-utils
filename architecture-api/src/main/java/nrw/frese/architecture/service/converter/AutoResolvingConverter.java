package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.model.KeyedObject;

public class AutoResolvingConverter<DTO extends KeyedObject<ID>, DB extends KeyedObject<ID>, ID> implements ObjectConverter<DTO, DB, ID> {
    @Override
    public DTO convertToDto(DB dbEntity) {
        return null;
    }

    @Override
    public DB convertToDb(DTO dtoEntity) {
        return null;
    }
}
