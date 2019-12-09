package nrw.frese.architecture.spring.service;

import nrw.frese.architecture.spring.SingleObjectSpringDataService;
import nrw.frese.architecture.spring.model.DbObject;
import nrw.frese.architecture.spring.repository.TestRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TestSpringService implements SingleObjectSpringDataService<DbObject, Long> {

    private TestRepository testRepository;

    public TestSpringService(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    @Override
    public CrudRepository<DbObject, Long> getDataAccessObject() {
        return testRepository;
    }

    @Override
    public Class<DbObject> getServiceClass() {
        return DbObject.class;
    }
}