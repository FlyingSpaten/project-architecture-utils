package nrw.frese.architecture.data;

import nrw.frese.architecture.model.TestObject;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class TestObjectRepository extends BaseDataRepository<TestObject> {

    @Getter
    @Setter
    private List<TestObject> objectList;

    public TestObjectRepository() {
        objectList = new LinkedList<>();
    }

}
