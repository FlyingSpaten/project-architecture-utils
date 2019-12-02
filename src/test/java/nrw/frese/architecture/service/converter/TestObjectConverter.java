package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.model.DbTestObject;
import nrw.frese.architecture.model.TestObject;

public class TestObjectConverter implements ObjectConverter<TestObject, DbTestObject, Long> {

    @Override
    public TestObject convertToDto(DbTestObject dbEntity) {
        if (dbEntity != null) {
            TestObject testObject = new TestObject();
            testObject.setId(dbEntity.getId());
            testObject.setName(dbEntity.getDb_name());
            return testObject;
        }
        return null;
    }

    @Override
    public DbTestObject convertToDb(TestObject dtoEntity) {
        DbTestObject dbTestObject = new DbTestObject();
        dbTestObject.setId(dtoEntity.getId());
        dbTestObject.setDb_name(dtoEntity.getName());
        return dbTestObject;
    }
}
