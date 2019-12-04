package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.model.DbTestObject;
import nrw.frese.architecture.model.TestObject;

public class DemoAutoConverter extends BaseAutoConverter<TestObject, DbTestObject, Long> {
    @Override
    protected TestObject getNewDtoObject() {
        return new TestObject();
    }

    @Override
    protected DbTestObject getNewDbObject() {
        return new DbTestObject();
    }
}
