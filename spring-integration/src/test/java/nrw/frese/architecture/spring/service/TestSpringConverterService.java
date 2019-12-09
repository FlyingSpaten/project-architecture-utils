package nrw.frese.architecture.spring.service;

import nrw.frese.architecture.service.converter.ObjectConverter;
import nrw.frese.architecture.spring.ConverterSpringDataService;
import nrw.frese.architecture.spring.model.DbObject;
import nrw.frese.architecture.spring.model.DtoObject;
import nrw.frese.architecture.spring.repository.TestRepository;
import nrw.frese.architecture.spring.service.converter.TestObjectAutoConverter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TestSpringConverterService implements ConverterSpringDataService<DtoObject, DbObject, Long> {

    private TestRepository testRepository;

    public TestSpringConverterService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public ObjectConverter<DtoObject, DbObject, Long> getConverter() {
        return new TestObjectAutoConverter();
    }

    @Override
    public CrudRepository<DbObject, Long> getDataAccessObject() {
        return testRepository;
    }

    @Override
    public Class<DbObject> getDbObjectClass() {
        return DbObject.class;
    }
}
