package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.configuration.Converter;
import nrw.frese.architecture.model.DbTestObject;
import nrw.frese.architecture.model.TestObject;

@Converter(dtoType = TestObject.class, dbType = DbTestObject.class)
public class AnnotatedDemoConverter extends BaseAutoConverter<TestObject, DbTestObject, Long> {

    @Override
    protected TestObject getNewDtoObject() {
        return new TestObject();
    }

    @Override
    protected DbTestObject getNewDbObject() {
        return new DbTestObject();
    }
}
