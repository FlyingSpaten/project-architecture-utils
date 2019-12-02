package nrw.frese.architecture.service;

import nrw.frese.architecture.data.DataAccessObject;
import nrw.frese.architecture.data.TestObjectAccessObject;
import nrw.frese.architecture.model.TestObject;

public class TestObjectService implements SingleObjectServiceService<TestObject, Long> {

    private TestObjectAccessObject testObjectAccessObject;

    public TestObjectService(){
        testObjectAccessObject = new TestObjectAccessObject();
    }

    @Override
    public DataAccessObject<TestObject, Long> getDataAccessObject() {
        return testObjectAccessObject;
    }

}
