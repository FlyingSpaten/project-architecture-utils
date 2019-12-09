package nrw.frese.architecture.spring.service;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.spring.model.DbObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleObjectSpringDataServiceTest extends BaseCrudElementTest<DbObject> {

    private static final String NEW_NAME = "new";

    private TestSpringService testService;

    @Autowired
    public void setTestService(TestSpringService testService) {
        this.testService = testService;
    }


    @Override
    protected DbObject getNewObject() {
        return new DbObject();
    }

    @Override
    protected DbObject modifyObject(DbObject object) {
        object.setName(NEW_NAME);
        return object;
    }

    @Override
    protected boolean isModified(DbObject object) {
        return object != null && object.getName() != null && object.getName().equals(NEW_NAME);
    }

    @Override
    protected CrudInterface<DbObject, Long> getCrudElement() {
        return testService;
    }
}
