package nrw.frese.architecture.service;

import nrw.frese.architecture.data.DataRepository;
import nrw.frese.architecture.data.DbTestObjectRepository;
import nrw.frese.architecture.model.DbTestObject;
import nrw.frese.architecture.model.TestObject;
import nrw.frese.architecture.service.converter.ObjectConverter;
import nrw.frese.architecture.service.converter.TestObjectConverter;

public class TestObjectConverterService implements ConverterService<TestObject, DbTestObject, Long> {

    private DbTestObjectRepository dbTestObjectAccessObject;

    public TestObjectConverterService(){
        dbTestObjectAccessObject = new DbTestObjectRepository();
    }

    @Override
    public ObjectConverter<TestObject, DbTestObject, Long> getConverter() {
        return new TestObjectConverter();
    }

    @Override
    public DataRepository<DbTestObject, Long> getDataAccessObject() {
        return dbTestObjectAccessObject;
    }
}
