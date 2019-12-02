package nrw.frese.architecture.service;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.model.TestObject;
import org.junit.BeforeClass;

public class TestConverterService extends BaseCrudElementTest<TestObject> {

    private static final String NEW_VALUE = "new";

    private static TestObjectConverterService testObjectConverterService;

    @BeforeClass
    public static void beforeClass(){
        testObjectConverterService = new TestObjectConverterService();
    }

    @Override
    protected TestObject getNewObject() {
        return new TestObject();
    }

    @Override
    protected TestObject modifyObject(TestObject object) {
        object.setName(NEW_VALUE);
        return object;
    }

    @Override
    protected boolean isModified(TestObject object) {
        return object != null && object.getName().equals(NEW_VALUE);
    }

    @Override
    protected CrudInterface<TestObject, Long> getCrudElement() {
        return testObjectConverterService;
    }
}
