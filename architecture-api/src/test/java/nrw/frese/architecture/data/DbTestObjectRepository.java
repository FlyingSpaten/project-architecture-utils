package nrw.frese.architecture.data;

import lombok.Getter;
import lombok.Setter;
import nrw.frese.architecture.model.DbTestObject;

import java.util.LinkedList;
import java.util.List;

public class DbTestObjectRepository extends BaseDataRepository<DbTestObject> {

    @Getter
    @Setter
    private List<DbTestObject> objectList;

    public DbTestObjectRepository() {
        objectList = new LinkedList<>();
    }

}
