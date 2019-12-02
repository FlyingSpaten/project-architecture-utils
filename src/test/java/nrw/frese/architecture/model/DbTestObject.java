package nrw.frese.architecture.model;

import lombok.Data;

@Data
public class DbTestObject implements KeyedObject<Long>{

    private Long id;

    private String db_name;

}
