package nrw.frese.architecture.spring.service.converter;

import nrw.frese.architecture.service.converter.BaseAutoConverter;
import nrw.frese.architecture.spring.model.DbObject;
import nrw.frese.architecture.spring.model.DtoObject;

public class TestObjectAutoConverter extends BaseAutoConverter<DtoObject, DbObject, Long> {

    @Override
    protected DtoObject getNewDtoObject() {
        return new DtoObject();
    }

    @Override
    protected DbObject getNewDbObject() {
        return new DbObject();
    }
}
