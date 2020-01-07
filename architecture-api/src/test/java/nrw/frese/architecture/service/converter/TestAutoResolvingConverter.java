package nrw.frese.architecture.service.converter;

import nrw.frese.architecture.configuration.ConverterConfiguration;
import nrw.frese.architecture.model.DbTestObject;
import nrw.frese.architecture.model.TestObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAutoResolvingConverter {

    private static AnnotatedDemoConverter annotatedDemoConverter;

    @BeforeClass
    public static void beforeClass() {
        annotatedDemoConverter = new AnnotatedDemoConverter();
        ConverterConfiguration.configure("nrw.frese.architecture.service.converter");
    }

    @Test
    public void testConvertToDto() {
        Long id = 42L;
        String name = "test";

        DbTestObject dbTestObject = new DbTestObject();
        dbTestObject.setId(id);
        dbTestObject.setName(name);

        TestObject testObject = annotatedDemoConverter.convertToDto(dbTestObject);
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

        DbTestObject dbTestObject = annotatedDemoConverter.convertToDb(testObject);
        assert dbTestObject.getId().equals(id);
        assert dbTestObject.getName().equals(name);
    }
}
