package nrw.frese.architecture.service;

import nrw.frese.architecture.data.DataAccessObject;
import nrw.frese.architecture.data.DbTestObjectAccessObject;
import nrw.frese.architecture.model.DbTestObject;
import nrw.frese.architecture.model.TestObject;
import nrw.frese.architecture.service.converter.ObjectConverter;
import nrw.frese.architecture.service.converter.TestObjectConverter;

public class TestObjectConverterService implements ConverterService<TestObject, DbTestObject, Long> {

    private DbTestObjectAccessObject dbTestObjectAccessObject;

    public TestObjectConverterService(){
        dbTestObjectAccessObject = new DbTestObjectAccessObject();
    }

    @Override
    public ObjectConverter<TestObject, DbTestObject, Long> getConverter() {
        return new TestObjectConverter();
    }

    @Override
    public DataAccessObject<DbTestObject, Long> getDataAccessObject() {
        return dbTestObjectAccessObject;
    }
}
