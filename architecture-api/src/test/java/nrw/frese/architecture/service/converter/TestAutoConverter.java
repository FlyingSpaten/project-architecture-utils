package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.model.DbTestObject;
import nrw.frese.architecture.model.TestObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAutoConverter {

    private static DemoAutoConverter demoAutoConverter;

    @BeforeClass
    public static void beforeClass() {
        demoAutoConverter = new DemoAutoConverter();
    }

    @Test
    public void testConvertToDto() {
        Long id = 42L;
        String name = "test";

        DbTestObject dbTestObject = new DbTestObject();
        dbTestObject.setId(id);
        dbTestObject.setName(name);

        TestObject testObject = demoAutoConverter.convertToDto(dbTestObject);
        assert testObject.getId().equals(id);
        assert testObject.getName().equals(name);
    }

    @Test
    public void testConvertToDb() {
        Long id = 42L;
        String name = "test";

        TestObject testObject = new TestObject();
        testObject.setId(id);
        testObject.setName(name);

        DbTestObject dbTestObject = demoAutoConverter.convertToDb(testObject);
        assert dbTestObject.getId().equals(id);
        assert dbTestObject.getName().equals(name);
    }

}
