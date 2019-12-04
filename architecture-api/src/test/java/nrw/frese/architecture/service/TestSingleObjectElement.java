package nrw.frese.architecture.service;

import nrw.frese.architecture.model.TestObject;
import nrw.frese.architecture.base.CrudInterface;
import org.junit.BeforeClass;

public class TestSingleObjectElement extends BaseCrudElementTest<TestObject> {

    public static TestObjectService testObjectService;

    private static final String NEW_NAME = "new";

    @BeforeClass
    public static void beforeClass() {
        testObjectService = new TestObjectService();
    }

    @Override
    protected TestObject getNewObject() {
        return new TestObject();
    }

    @Override
    protected TestObject modifyObject(TestObject object) {
        object.setName(NEW_NAME);
        return object;
    }

    @Override
    protected boolean isModified(TestObject object) {
        return object.getName() != null && object.getName().equals(NEW_NAME);
    }

    @Override
    protected CrudInterface<TestObject, Long> getCrudElement() {
        return testObjectService;
    }
}
