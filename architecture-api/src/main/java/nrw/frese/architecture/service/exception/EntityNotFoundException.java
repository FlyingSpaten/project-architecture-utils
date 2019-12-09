package nrw.frese.architecture.service.exception;

import nrw.frese.architecture.model.KeyedObject;

public class EntityNotFoundException extends RuntimeException {

    public <T extends KeyedObject<ID>, ID> EntityNotFoundException(Class<T> entityType, ID id) {
        super(String.format("No entity was found for type %s and id %s", entityType.getName(), id));
    }

}
