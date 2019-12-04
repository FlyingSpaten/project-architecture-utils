package nrw.frese.architecture.model;

import lombok.Data;

@Data
public class TestObject implements KeyedObject<Long>{

    private Long id;

    private String name;

}
