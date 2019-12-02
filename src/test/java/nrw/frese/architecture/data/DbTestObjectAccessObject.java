package nrw.frese.architecture.data;

import lombok.Getter;
import lombok.Setter;
import nrw.frese.architecture.model.DbTestObject;

import java.util.LinkedList;
import java.util.List;

public class DbTestObjectAccessObject extends BaseDataAccessObject<DbTestObject> {

    @Getter
    @Setter
    private List<DbTestObject> objectList;

    public DbTestObjectAccessObject() {
        objectList = new LinkedList<>();
    }

}
