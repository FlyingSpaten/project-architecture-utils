package nrw.frese.architecture.service;

import nrw.frese.architecture.data.TestObjectRepository;
import nrw.frese.architecture.model.TestObject;
import nrw.frese.architecture.data.DataRepository;

public class TestObjectService implements SingleObjectService<TestObject, Long> {

    private TestObjectRepository testObjectAccessObject;

    public TestObjectService(){
        testObjectAccessObject = new TestObjectRepository();
    }

    @Override
    public DataRepository<TestObject, Long> getDataAccessObject() {
        return testObjectAccessObject;
    }

}
