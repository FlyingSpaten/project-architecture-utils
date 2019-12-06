package nrw.frese.architecture.spring.model;

import lombok.Data;
import nrw.frese.architecture.model.KeyedObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class DbObject implements KeyedObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
