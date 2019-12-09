package nrw.frese.architecture.spring.model;

import lombok.Data;
import nrw.frese.architecture.model.KeyedObject;

@Data
public class DtoObject implements KeyedObject<Long> {

    private Long id;

    private String name;

}
