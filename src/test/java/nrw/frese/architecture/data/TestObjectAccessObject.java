package nrw.frese.architecture.data;

import lombok.Getter;
import lombok.Setter;
import nrw.frese.architecture.model.TestObject;

import java.util.LinkedList;
import java.util.List;

public class TestObjectAccessObject extends BaseDataAccessObject<TestObject> {

    @Getter
    @Setter
    private List<TestObject> objectList;

    public TestObjectAccessObject() {
        objectList = new LinkedList<>();
    }

}
