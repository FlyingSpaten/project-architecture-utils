package nrw.frese.architecture.spring.repository;

import nrw.frese.architecture.spring.model.DbObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryIntegrationTest {

    private TestRepository testRepository;

    @Autowired
    public void setTestRepository(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    @Test
    public void testRepository() {
        DbObject dbObject = new DbObject();
        assert dbObject.getId() == null || dbObject.getId() <= 0;
        dbObject = testRepository.add(dbObject);
        assert dbObject.getId() > 0;
    }

}
