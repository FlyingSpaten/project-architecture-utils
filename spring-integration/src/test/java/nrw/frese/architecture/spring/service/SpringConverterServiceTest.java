package nrw.frese.architecture.spring.service;

import nrw.frese.architecture.base.CrudInterface;
import nrw.frese.architecture.spring.model.DtoObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringConverterServiceTest extends BaseCrudElementTest<DtoObject> {

    private static final String NEW_NAME = "new";

    private TestSpringConverterService testSpringConverterService;

    @Autowired
    public void setTestSpringConverterService(TestSpringConverterService testSpringConverterService) {
        this.testSpringConverterService = testSpringConverterService;
    }

    @Override
    protected DtoObject getNewObject() {
        return new DtoObject();
    }

    @Override
    protected DtoObject modifyObject(DtoObject object) {
        object.setName(NEW_NAME);
        return object;
    }

    @Override
    protected boolean isModified(DtoObject object) {
        return object != null && object.getName() != null && object.getName().equals(NEW_NAME);
    }

    @Override
    protected CrudInterface<DtoObject, Long> getCrudElement() {
        return testSpringConverterService;
    }
}
